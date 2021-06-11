package com.example.news.model

import com.example.news.model.Article
import com.google.gson.annotations.SerializedName


data class Result(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)