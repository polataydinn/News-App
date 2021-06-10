package com.example.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.data.Article
import com.example.news.databinding.NewsItemBinding

class NewsAdapter(newsList: List<Article>) : RecyclerView.Adapter<NewsViewHolder>() {

    private var mNewsList = newsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(mNewsList[position])
    }

    override fun getItemCount() = mNewsList.size
}
