package com.example.news.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.news.model.Article

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNews(article: Article)

    @Query("SELECT * FROM Article WHERE urlToImage IS NOT NULL")
    fun getAllColumns() : LiveData<List<Article>>

    @Query("SELECT * FROM Article WHERE isFavorite")
    fun getFavorites() : LiveData<List<Article>>

    @Query("SELECT * FROM Article order by publishedAt DESC LIMIT 10")
    fun getLatestNews() : LiveData<List<Article>>

    @Update
    suspend fun updateArticle(article: Article)
}
