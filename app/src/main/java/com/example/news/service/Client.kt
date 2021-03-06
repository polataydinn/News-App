package com.example.news.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Client {

    val baseUrl = "https://newsapi.org/"

    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(1000, TimeUnit.MILLISECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create()).build()

    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}


