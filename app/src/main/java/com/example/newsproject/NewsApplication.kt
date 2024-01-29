package com.example.newsproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm


@HiltAndroidApp
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}