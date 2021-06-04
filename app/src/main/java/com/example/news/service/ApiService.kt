package com.example.news.service

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("v2/top-headlines?country=us&category=business&apiKey=8e2416e85bd14d2e81d8d6a41b9abe05")
    fun getNews(): Call<Result>
}