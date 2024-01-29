package com.example.newsproject.news

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.data.model.Article
import com.example.newsproject.data.repository.NewsRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.RealmList
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepositoryInterface
) : ViewModel() {

    private val _data = MutableLiveData<List<Article>>()
    val data: LiveData<List<Article>> = _data

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {

        getNews()
    }

    @VisibleForTesting
    internal fun getNews() {
        viewModelScope.launch {

            try {

                _isLoading.value = true

                val response = newsRepository.getNewsFromNetwork()
                if (response.isSuccessful) {
                    response.body()?.let {
                        newsRepository.saveNewsToLocal(it)
                        _data.postValue(filterValidArticles(it.articles))
                    }
                }

            } catch (e: IOException) {

                val response = newsRepository.getNewsFromLocal()
                if (response.isSuccessful) {
                    _data.postValue(response.body()?.articles?.let { filterValidArticles(it) })
                }

            } finally {

                _isLoading.postValue(false)
            }
        }
    }

    private fun filterValidArticles(articles: RealmList<Article>): List<Article> {
        return articles.filterNot {
            it.title.isEmpty() || it.urlToImage.isNullOrEmpty()
        }
    }
}