package com.example.news.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.news.model.Article


@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun initializeDatabase(context: Context) {
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "breakingNews"
                ).build()
            }
        }

        fun getDatabase() = INSTANCE
    }
}