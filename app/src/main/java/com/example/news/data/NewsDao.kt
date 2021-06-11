package com.example.news.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.news.model.Article

@Dao
interface NewsDao {
    @Insert
    suspend fun addNews(article: Article)

    @Query("SELECT * FROM Article")
    suspend fun getAllColumns() : List<Article>
}