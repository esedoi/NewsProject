package com.example.newsproject.data.local

import com.example.newsproject.data.model.NewsResponse
import retrofit2.Response


interface LocalDataSourceInterface {
    suspend fun getNews(): Response<NewsResponse>

    suspend fun saveNews(newsResponse: NewsResponse)
}