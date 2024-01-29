package com.example.newsproject.data.repository

import com.example.newsproject.data.model.NewsResponse
import retrofit2.Response


interface NewsRepositoryInterface {
    suspend fun getNewsFromNetwork(): Response<NewsResponse>
    suspend fun getNewsFromLocal(): Response<NewsResponse>
    suspend fun saveNewsToLocal(newsResponse: NewsResponse)

}