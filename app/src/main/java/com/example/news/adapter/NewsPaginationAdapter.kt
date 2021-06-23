package com.example.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.news.databinding.NewsItemBinding
import com.example.news.model.Article

class NewsPaginationAdapter(val onItemClickListener: (Int, Boolean) -> Unit) :
    PagingDataAdapter<Article, NewsViewHolder>(NewsAdapter.DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onItemClickListener) }
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
                getItem(position)?.let {
                    holder.bind(
                        it,
                        onItemClickListener,
                        getItem(position)?.isFavorite == true
                    )
                }
            }
        }
    }

}
