package com.example.newsproject.di

import android.content.Context
import com.example.newsproject.data.repository.NewsRepository
import com.example.newsproject.data.repository.NewsRepositoryInterface
import com.example.newsproject.data.local.LocalDataSource
import com.example.newsproject.data.local.LocalDataSourceInterface
import com.example.newsproject.data.network.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NewsApiService =
        retrofit.create(NewsApiService::class.java)

    @Provides
    @Singleton
    fun provideLocalDataSource(): LocalDataSourceInterface =
        LocalDataSource()

    @Provides
    @Singleton
    fun provideDataRepository(
        apiService: NewsApiService,
        localDataSource: LocalDataSource
    ): NewsRepositoryInterface = NewsRepository(apiService, localDataSource)

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }
}