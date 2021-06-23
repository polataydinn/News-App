package com.example.news.data

import com.example.news.model.Article

object NewsRepository {

    private val newsDao by lazy {
        NewsDatabase.getDatabase()?.newsDao()
    }

    fun readAllNews() = newsDao?.getAllColumns()

    fun getLatestNews() = newsDao?.getLatestNews()

    suspend fun addNewsToDB(article: Article) {
        newsDao?.addNews(article)
    }

    suspend fun updateAricle(article: Article) {
        newsDao?.updateArticle(article)
    }

    fun getFavorites() = newsDao?.getFavorites()


}