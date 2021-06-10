package com.example.news.service

import com.example.news.data.Article
import com.google.gson.annotations.SerializedName


data class Result(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)