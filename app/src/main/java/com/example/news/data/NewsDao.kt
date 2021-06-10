package com.example.news.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface NewsDao {
    @Insert
    suspend fun addNews(article: Article)
}