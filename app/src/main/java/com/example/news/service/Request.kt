package com.example.news.service

import android.util.Log
import com.example.news.model.Article
import com.example.news.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object Request {
    fun getNews(onResponse: (List<Article>) -> Unit) {
        Client.getRetrofit()?.let { retrofit ->
            val call = retrofit.getNews()
            call.enqueue(object : Callback<Result> {
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    Log.d("Dosya Çekim", "Başarılıı")
                    val list = response.body()!!.articles

                    if (list != null) {
                        onResponse(list)
                    }

                }

                override fun onFailure(call: Call<Result>, t: Throwable) {
                    Log.d("Dosya Çekim: ", "Hata " + t)
                }
            })
        }
    }
}