package com.example.news.service

import com.example.news.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines?country=us&category=business&apiKey=8e2416e85bd14d2e81d8d6a41b9abe05")
    fun getNews(@Query("pageSize" )pageSize :Int = 20, @Query("page")page : Int = 1): Call<Result>
}