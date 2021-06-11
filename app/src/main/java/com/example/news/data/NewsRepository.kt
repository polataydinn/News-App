package com.example.news.data

import com.example.news.model.Article

class NewsRepository {

    private val newsDao by lazy {
        NewsDatabase.getDatabase()?.newsDao()
    }

    suspend fun readAllNews(): List<Article>? = newsDao?.getAllColumns()

    suspend fun addNewsToDB(article: Article) {
        newsDao?.addNews(article)
    }
}