package com.example.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.news.model.Article
import com.example.news.databinding.NewsItemBinding

class NewsAdapter(val onItemClickListener: (Int) -> Unit)
    : ListAdapter<Article,NewsViewHolder>(DiffCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position),onItemClickListener)
    }

    class DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem.title == newItem.title


        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem == newItem
    }
}
