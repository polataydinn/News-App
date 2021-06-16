package com.example.news.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.news.adapter.NewsAdapter
import com.example.news.databinding.FragmentFavoritesBinding
import com.example.news.model.Article
import com.example.news.ui.breaking.BreakingViewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NewsAdapter
    private lateinit var viewModel: BreakingViewModel
    private val favoriteTempList = arrayListOf<Article>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BreakingViewModel::class.java)
        adapter = NewsAdapter {
            val currentNews = adapter.currentList[it]
              viewModel.updateFavorite(currentNews.copy(isFavorite = !currentNews.isFavorite))
        }

        viewModel.apply {
            getBreakingNews()
            news?.observe(viewLifecycleOwner) { listOfNews ->
                adapter.submitList(listOfNews.filter { it.isFavorite }.asReversed())
                binding.favoriteList.adapter = adapter
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}