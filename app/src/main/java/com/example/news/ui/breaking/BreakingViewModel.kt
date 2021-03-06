package com.example.news.ui.breaking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.NewsRepository
import com.example.news.model.Article
import com.example.news.service.Request
import kotlinx.coroutines.launch


class BreakingViewModel : ViewModel() {
    private var _news = getLatestNews()
    val news = _news


    fun updateFavorite(article: Article) {
        viewModelScope.launch {
            updateAricle(article)
        }
    }
    fun getLatestNews() = NewsRepository.getLatestNews()

    suspend fun updateAricle(article: Article) = NewsRepository.updateAricle(article)
}