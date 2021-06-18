package com.example.news.ui.breaking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.news.adapter.NewsAdapter
import com.example.news.databinding.FragmentBreakingBinding
import com.example.news.model.TempNews
import com.example.news.ui.all.AllNewsFragmentDirections

class BreakingFragment : Fragment() {

    private var _binding: FragmentBreakingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BreakingViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreakingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BreakingViewModel::class.java)

        viewModel.apply {
            getLatestNews()
            news?.observe(viewLifecycleOwner) { listOfBreking ->
                adapter.submitList(listOfBreking)
            }
        }

        adapter = NewsAdapter { position, isHeart ->
            val currentNews = adapter.currentList[position]
            if (isHeart) {
                val result = currentNews.copy(isFavorite = !currentNews.isFavorite)
                viewModel.updateFavorite(result)
            } else {
                val tempNews =
                    TempNews(currentNews.title, currentNews.content, currentNews.urlToImage)
                val redirect =
                    BreakingFragmentDirections.actionNavigationHomeToShowNewsFragment(tempNews)
                Navigation.findNavController(view).navigate(redirect)
            }
        }
        binding.breakingNewsList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}