package com.example.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.news.model.Source
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Article")
data class Article(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: Source?,
    @PrimaryKey
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
)