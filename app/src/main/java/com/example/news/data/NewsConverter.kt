package com.example.news.data

import androidx.room.TypeConverter
import com.example.news.model.Source

class NewsConverter {
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}