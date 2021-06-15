package com.example.news.ui.breaking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.news.adapter.NewsAdapter
import com.example.news.databinding.FragmentBreakingBinding
import com.example.news.ui.loading.LoadingScreen

class BreakingFragment : Fragment() {

    var recyclerView: RecyclerView? = null

    private var _binding: FragmentBreakingBinding? = null
    private val binding get() = _binding!!
    private var _isRefreshing : Boolean? = null

    private lateinit var viewModel: BreakingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBreakingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.breakingNewsList
        recyclerView?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        viewModel = ViewModelProvider(this).get(BreakingViewModel::class.java)

        val loadingScreen = LoadingScreen(activity)

        viewModel.isLoading.observe(viewLifecycleOwner){
            if (it) loadingScreen.startLoadingScreen()
            else loadingScreen.dismissLoadingScreen()
        }



        viewModel.refreshTheNews()
        viewModel.getBreakingNews()
        viewModel.news.observe(viewLifecycleOwner){breakingNewsList ->
            recyclerView?.adapter = NewsAdapter(breakingNewsList)
        }

        binding.swipeToRefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            viewModel.refreshTheNews()
            viewModel.isRefreshing.observe(viewLifecycleOwner){
                    binding.swipeToRefresh.isRefreshing = it
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}