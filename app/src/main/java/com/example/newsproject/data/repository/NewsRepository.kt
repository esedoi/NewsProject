package com.example.newsproject.data.repository

import com.example.newsproject.data.local.LocalDataSourceInterface
import com.example.newsproject.data.model.NewsResponse
import com.example.newsproject.data.network.NewsApiService
import retrofit2.Response
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService,
    private val localDataSource: LocalDataSourceInterface
) : NewsRepositoryInterface {
    override suspend fun getNewsFromNetwork(): Response<NewsResponse> = newsApiService.getNews()
    override suspend fun getNewsFromLocal(): Response<NewsResponse> =
        localDataSource.getNews()

    override suspend fun saveNewsToLocal(newsResponse: NewsResponse) =
        localDataSource.saveNews(newsResponse)

}