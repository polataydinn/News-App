package com.example.news.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TempNews(
    val title: String,
    val content : String?,
    val imageUrl : String?
) : Parcelable
