package com.example.news.app

import android.app.Application
import com.example.news.data.NewsDatabase

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        NewsDatabase.initializeDatabase(applicationContext)
    }
}