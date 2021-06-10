package com.example.news.data

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val _id: Any,
    @SerializedName("name")
    val name: String
)