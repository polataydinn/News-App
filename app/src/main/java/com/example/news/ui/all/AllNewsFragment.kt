package com.example.news.ui.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.news.data.NewsRepository
import com.example.news.databinding.FragmentAllBinding

class AllNewsFragment : Fragment() {

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!
    private val repository =  NewsRepository()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.allTextView
        textView.setText("All news Fragment")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.getData.setOnClickListener(View.OnClickListener {

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}