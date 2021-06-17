package com.example.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.news.databinding.FragmentShowNewsBinding

class ShowNewsFragment : Fragment() {

    private var _binding: FragmentShowNewsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ShowNewsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(binding.showNewsFragment)
            .load(args.currentArticle.imageUrl)
            .into(binding.toolbarImage)

        binding.contentText.text = args.currentArticle.content

    }


}