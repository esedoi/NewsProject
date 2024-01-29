package com.example.newsproject.data.network

import com.example.newsproject.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET


interface NewsApiService {

    @GET("v2/top-headlines?country=us&apiKey=ba409522176742c2bdfcc96bdab3a2c8")
    suspend fun getNews(): Response<NewsResponse>

}