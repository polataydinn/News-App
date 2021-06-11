package com.example.news.data

import androidx.room.Dao
import androidx.room.Insert
import com.example.news.model.Article

@Dao
interface NewsDao {
    @Insert
    suspend fun addNews(article: Article)
}