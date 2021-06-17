package com.example.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.news.databinding.NewsItemBinding
import com.example.news.model.Article

class NewsAdapter(val onItemClickListener: (Int, Boolean) -> Unit) :
    ListAdapter<Article, NewsViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads[0] == true) {
                holder.bind(getItem(position), onItemClickListener, getItem(position).isFavorite)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem.title == newItem.title


        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem == newItem

        override fun getChangePayload(oldItem: Article, newItem: Article): Any? {
            return if (oldItem.isFavorite != newItem.isFavorite) true
            else null
        }
    }

}
