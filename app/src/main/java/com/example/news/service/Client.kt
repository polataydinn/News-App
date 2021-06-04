package com.example.news.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {

    val baseUrl = "https://newsapi.org/"

    fun getRetrofit(): ApiService? {
        baseUrl?.let {
            return Retrofit.Builder()
                .baseUrl(it)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
        return null
    }
}