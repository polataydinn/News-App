package com.example.news.ui.all

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.adapter.NewsPaginationAdapter
import com.example.news.databinding.FragmentAllBinding
import com.example.news.model.TempNews
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AllNewsFragment : Fragment() {

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AllNewsViewModel
    private lateinit var adapter: NewsPaginationAdapter
    var dialog: AlertDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AllNewsViewModel::class.java)

        viewModel.apply {
            isLoading.observe(viewLifecycleOwner) {
                if (it) startLoadingScreen() else dismissLoadingScreen()
            }
            getBreakingNews()
            isRefreshing.observe(viewLifecycleOwner) {
                binding.swipeToRefresh.isRefreshing = it
            }


        }

        adapter = NewsPaginationAdapter { position, isHeart ->
            val currentNews = adapter.snapshot()[position]
            if (isHeart) {
                val result = currentNews?.copy(isFavorite = !currentNews.isFavorite)
                lifecycleScope.launch {
                    if (result != null) {
                        viewModel.insertToDB(result)
                    }
                }
            } else {
                val tempNews =
                    TempNews(currentNews!!.title, currentNews.content, currentNews.urlToImage)
                val redirect =
                    AllNewsFragmentDirections.actionNavigationDashboardToShowNewsFragment(tempNews)
                Navigation.findNavController(view).navigate(redirect)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.listData.collect {
                adapter.submitData(it)
            }
        }

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.refresTheNews()
        }

        binding.allNewList.adapter = adapter

    }

    private fun startLoadingScreen() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setView(layoutInflater.inflate(R.layout.loading_screen, null))
        builder.setCancelable(true)
        dialog = builder.create()
        dialog?.show()
    }

    private fun dismissLoadingScreen() {
        dialog?.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
