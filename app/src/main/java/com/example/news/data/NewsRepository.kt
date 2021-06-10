package com.example.news.data

class NewsRepository {

    private val newsDao  by lazy {
        NewsDatabase.getDatabase()?.newsDao()
    }
    //val readAllNews: LiveData<List<Result.Article>> = newsDao?.readAllNews()!!


    suspend fun addNewsToDB(article: Article) {
        newsDao?.addNews(article)
    }
}