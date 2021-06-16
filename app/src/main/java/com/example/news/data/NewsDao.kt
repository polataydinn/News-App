package com.example.news.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.news.model.Article

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNews(article: Article)

    @Query("SELECT * FROM Article")
    fun getAllColumns() : LiveData<List<Article>>

    @Query("SELECT * FROM Article WHERE isFavorite")
    suspend fun getFavorites() : List<Article>

    @Update
    suspend fun updateArticle(article: Article)
}
