package com.example.news.ui.favorites

import android.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.adapter.NewsAdapter
import com.example.news.data.NewsRepository
import com.example.news.databinding.FragmentAllBinding
import com.example.news.model.Article
import com.example.news.service.Request
import com.example.news.ui.all.AllNewsViewModel
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {
    private var _news = getFavorites()
    val news = _news


    fun updateFavorite(article: Article) {
        viewModelScope.launch {
            updateAricle(article)
        }
    }

    fun getFavorites() = NewsRepository.getFavorites()

    suspend fun updateAricle(article: Article) = NewsRepository.updateAricle(article)
}