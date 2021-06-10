package com.example.news.ui.breaking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.Article
import com.example.news.data.NewsRepository
import com.example.news.service.Request
import kotlinx.coroutines.launch


class BreakingViewModel : ViewModel() {
    val news : MutableLiveData<List<Article>> by lazy {
        MutableLiveData<List<Article>>()
    }

    private val repository =  NewsRepository()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    var isRequested = false

    fun getBreakingNews(){
        if (isRequested) return else isRequested = true
        isLoading.value = true
        Request.getNews { breakingNewsList ->
            news.value = breakingNewsList
            isLoading.value = false
            breakingNewsList.forEach {
                viewModelScope.launch {
                    insert(it)
                }
            }
        }
    }

    suspend fun insert(article :Article) = repository.addNewsToDB(article)
}