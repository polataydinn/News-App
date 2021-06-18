package com.example.news.ui.all

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.NewsRepository
import com.example.news.model.Article
import com.example.news.service.Request
import kotlinx.coroutines.launch

class AllNewsViewModel : ViewModel() {
    private var _news = readAllData()
    val news = _news


    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData(false)

    var isRequested = false

    fun getBreakingNews() {
        if (isRequested) return else isRequested = true
        sendRequest()
    }

    fun refresTheNews() {
        isRefreshing.value = true
        sendRequest()
    }

    fun sendRequest() {
        Request.getNews { breakingNewsList ->
            isLoading.value = false
            isRefreshing.value = false
            viewModelScope.launch {
                breakingNewsList.forEach {
                    insertToDB(it)
                }
            }
        }
    }

    fun updateFavorite(article: Article) {
        viewModelScope.launch {
            updateAricle(article)
        }
    }

    suspend fun insertToDB(article: Article) = NewsRepository.addNewsToDB(article)

    private fun readAllData() = NewsRepository.readAllNews()

    suspend fun updateAricle(article: Article) = NewsRepository.updateAricle(article)

}