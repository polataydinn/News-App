package com.example.news.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.news.R
import com.example.news.databinding.NewsItemBinding
import com.example.news.model.Article

class NewsViewHolder(private val binding: NewsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Article, onItemClickListener: (Int, Boolean) -> Unit) {
        binding.newsDescription.text = item.description
        binding.newsTitle.text = item.title
        Glide.with(binding.newsImage).load(item.urlToImage)
            .diskCacheStrategy(DiskCacheStrategy.DATA).into(binding.newsImage)

        binding.heartImage.setOnClickListener {
            onItemClickListener(adapterPosition, true)
        }
        binding.newsImage.setOnClickListener {
            onItemClickListener(adapterPosition, false)
        }

        if (item.isFavorite) {
            binding.heartImage.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.heartImage.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    fun bind(item: Article, onItemClickListener: (Int, Boolean) -> Unit, isFavorite: Boolean) {
        binding.heartImage.setOnClickListener {
            onItemClickListener(adapterPosition, true)
        }

        if (item.isFavorite) {
            binding.heartImage.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.heartImage.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

}

