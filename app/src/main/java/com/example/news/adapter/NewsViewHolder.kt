package com.example.news.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.NewsItemBinding
import com.example.news.model.Article

class NewsViewHolder(private val binding: NewsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Article, onItemClickListener: (Int) -> Unit) {
        binding.newsDescription.setText(item.description)
        binding.newsTitle.setText(item.title)
        Glide.with(binding.newsImage).load(item.urlToImage).into(binding.newsImage)

        binding.heartImage.setOnClickListener(View.OnClickListener {
            onItemClickListener(adapterPosition)
        })

        if (item.isFavorite) {
            binding.heartImage.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.heartImage.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}
