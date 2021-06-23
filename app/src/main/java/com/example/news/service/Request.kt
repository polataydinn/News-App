package com.example.news.service

import android.util.Log
import com.example.news.model.Article
import com.example.news.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object Request {
    fun getNews(onResponse: (List<Article>) -> Unit) {
        Client.retrofit?.let { retrofit ->
            Client.api.getAllNews().enqueue(object : Callback<Result> {
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    response.body()?.articles?.let(onResponse)
                }

                override fun onFailure(call: Call<Result>, t: Throwable) {
                    Log.d("Dosya Çekim: ", "Hata " + t)
                }
            })
        }
    }

    suspend fun getNews2(page: Int = 1, pageSize: Int = 20) = Client.api.getAllNews2(page,pageSize)
}