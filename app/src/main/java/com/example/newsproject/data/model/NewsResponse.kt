package com.example.newsproject.data.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class NewsResponse(
    @PrimaryKey
    var status: String = "",
    var totalResults: Int = 0,
    var articles: RealmList<Article> = RealmList()
) : RealmObject()

open class Article(

    var source: Source? = null,
    var author: String? = null,
    var title: String = "",
    var description: String? = null,
    @PrimaryKey
    var url: String = "",
    var urlToImage: String? = null,
    var publishedAt: String = "",
    var content: String? = null
) : RealmObject()

open class Source(
    @PrimaryKey
    var id: String? = null,
    var name: String = ""
) : RealmObject()
