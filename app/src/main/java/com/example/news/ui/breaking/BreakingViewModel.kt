package com.example.news.ui.breaking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.service.Result
import com.example.news.service.Request
import retrofit2.Response

class BreakingViewModel : ViewModel() {
    val news : MutableLiveData<List<Result.Article>> by lazy {
        MutableLiveData<List<Result.Article>>()
    }

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    var isRequested = false

    fun getBreakingNews(){
        if (isRequested) return else isRequested = true
        isLoading.value = true
        Request.getNews { breakingNewsList ->
            news.value = breakingNewsList
            isLoading.value = false
        }
    }
}