package com.example.news.ui.breaking

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.NewsRepository
import com.example.news.model.Article
import com.example.news.service.Request
import kotlinx.coroutines.launch


class BreakingViewModel : ViewModel() {
    val news: MutableLiveData<List<Article>> by lazy {
        MutableLiveData<List<Article>>()
    }

    private val repository = NewsRepository()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData(false)

    var isRequested = false
    var currentData: List<Article>? = null

    fun getBreakingNews() {
        if (isRequested) return else isRequested = true
        isLoading.value = true

        viewModelScope.launch {
            currentData = readAllData()
            if (currentData?.size != 0) {
                isLoading.value = false
                news.value = currentData
            } else {
                Request.getNews { breakingNewsList ->
                    isLoading.value = false
                    breakingNewsList.forEach {
                        viewModelScope.launch {
                            breakingNewsList.forEach({
                                insertToDB(it)
                            })
                            currentData = readAllData()
                            news.value = currentData
                        }
                    }
                }
            }
        }
    }

    fun refreshTheNews() {
        isRefreshing.value = true
            Request.getNews { breakingNewsList ->
                isRefreshing.value = false
                viewModelScope.launch {
                    breakingNewsList.forEach {
                        insertToDB(it)
                    }
                }
            }
    }

    suspend fun insertToDB(article: Article) = repository.addNewsToDB(article)

    suspend fun readAllData() = repository.readAllNews()
}