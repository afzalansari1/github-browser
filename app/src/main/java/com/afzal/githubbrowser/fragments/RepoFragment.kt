package com.afzal.githubbrowser.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.afzal.githubbrowser.RepoViewModel
import com.afzal.githubbrowser.adapters.RepoAdapter
import com.afzal.githubbrowser.databinding.FragmentRepoBinding
import com.afzal.githubbrowser.entities.Repo
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RepoFragment : Fragment() {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!
    private val repoItems = mutableListOf<Repo>()
    lateinit var viewModel: RepoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.addButtonEmpty.setOnClickListener {
            val action = RepoFragmentDirections.actionRepoFragmentToAddRepoFragment()
            findNavController().navigate(action)
        }

        setToolBar()

        val recyclerView = binding.repoRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RepoAdapter(requireContext(), repoItems)
        recyclerView.adapter = adapter

        adapter.onItemClick = { _, pos ->
            val action = RepoFragmentDirections.actionRepoFragmentToRepoDetailFragment(
                orgName = repoItems[pos].owner,
                repoName = repoItems[pos].repoName,
                description = repoItems[pos].description,
            )
            findNavController().navigate(action)
        }

        adapter.onItemLongClick = { _, position ->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete Repository")
                .setMessage("Are you sure you want to delete entry of ${repoItems[position].owner}/${repoItems[position].repoName}?")
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("Delete") { dialog, _ ->
                    viewModel.deleteRepo(repoItems[position])
                    dialog.dismiss()
                }
                .show()
        }

        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(
                Application()
            )
        )[RepoViewModel::class.java]
        viewModel.allRepoItems.observe(viewLifecycleOwner) {
            it?.let {
                adapter.updateClassItems(it)
                if (it.isEmpty()) {
                    binding.ifEmptyRepos.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun setToolBar() {
        val toolbar = binding.toolbarRepofrag
        toolbar.toolbarTitle.text = "Github Browser"
        toolbar.toolbarSubtitle.visibility = View.GONE
        toolbar.backButton.visibility = View.INVISIBLE
        toolbar.deleteButton.visibility = View.INVISIBLE
        toolbar.openBrowser.visibility = View.INVISIBLE

        toolbar.addButton.setOnClickListener {
            val action = RepoFragmentDirections.actionRepoFragmentToAddRepoFragment()
            findNavController().navigate(action)
        }
    }


}