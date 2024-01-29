package com.example.newsproject.data.local

import com.example.newsproject.data.model.NewsResponse
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class LocalDataSource @Inject constructor() : LocalDataSourceInterface {
    override suspend fun getNews(): Response<NewsResponse> = withContext(Dispatchers.IO) {

        Realm.getDefaultInstance().use { realm ->
            val newsResponse = realm.where(NewsResponse::class.java).findFirst()
            val newsResponseCopy = newsResponse?.let { realm.copyFromRealm(it) }

            newsResponseCopy?.let {
                Response.success(it)
            } ?: Response.error(500, ResponseBody.create(null, "no_data"))
        }
    }

    override suspend fun saveNews(newsResponse: NewsResponse) {

        withContext(Dispatchers.IO) {

            Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction { transactionRealm ->
                    transactionRealm.copyToRealmOrUpdate(newsResponse)
                }
            }
        }
    }
}