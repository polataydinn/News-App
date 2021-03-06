package com.example.news.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.news.adapter.NewsAdapter
import com.example.news.databinding.FragmentFavoritesBinding
import com.example.news.model.TempNews

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NewsAdapter
    private lateinit var viewModel: FavoritesViewModel

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

        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        viewModel.apply {
            getFavorites()
            news?.observe(viewLifecycleOwner) { listOfFavorites ->
                adapter.submitList(listOfFavorites.filter { it.isFavorite }.asReversed())
            }
        }

        adapter = NewsAdapter { position, isHeart ->
            val currentNews = adapter.currentList[position]

            if (isHeart) {
                viewModel.updateFavorite(currentNews.copy(isFavorite = !currentNews.isFavorite))
            } else {
                val tempNews =
                    TempNews(currentNews.title, currentNews.content, currentNews.urlToImage)
                val redirect =
                    FavoritesFragmentDirections.actionNavigationNotificationsToShowNewsFragment(
                        tempNews
                    )
                Navigation.findNavController(view).navigate(redirect)
            }
        }
        binding.favoriteList.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}