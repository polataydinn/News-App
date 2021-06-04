package com.example.news.ui.breaking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.news.databinding.FragmentBreakingBinding

class BreakingFragment : Fragment() {

    private var _binding: FragmentBreakingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBreakingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.breakingTextView
        textView.setText("Breaking Text")
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}