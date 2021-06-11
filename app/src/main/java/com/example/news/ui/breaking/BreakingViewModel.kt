package com.example.news.ui.breaking

import androidx.lifecycle.LiveData
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

    var isRequested = false
    var currentData : List<Article>? = null

    fun getBreakingNews() {
        if (isRequested) return else isRequested = true
        isLoading.value = true
        Request.getNews { breakingNewsList ->
            news.value = breakingNewsList
            isLoading.value = false
            breakingNewsList.forEach {
                viewModelScope.launch {
                    currentData = readAllData()
                    currentData?.forEach {current->
                        if (current.description == it.description){

                        }else

                            insertToDB(it)
                    }
                }
            }
        }
    }

    suspend fun insertToDB(article: Article) = repository.addNewsToDB(article)

    suspend fun readAllData() = repository.readAllNews()
}