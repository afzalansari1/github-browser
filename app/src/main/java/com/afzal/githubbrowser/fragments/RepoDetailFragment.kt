package com.afzal.githubbrowser.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afzal.githubbrowser.databinding.FragmentRepoDetailBinding

class RepoDetailFragment : Fragment() {

    private var _binding: FragmentRepoDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRepoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

}