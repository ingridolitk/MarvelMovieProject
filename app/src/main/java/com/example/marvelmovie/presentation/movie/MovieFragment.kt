package com.example.marvelmovie.presentation.movie

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelmovie.R
import com.example.marvelmovie.databinding.FragmentMovieBinding
import com.example.marvelmovie.model.ApiResult
import com.example.marvelmovie.model.MovieResult
import com.example.marvelmovie.presentation.adapter.MovieAdapter
import com.example.marvelmovie.presentation.descripton.DescriptionFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {
    private val viewModel: MovieViewModel by viewModel()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupRecycleView()
        observeViewModel()
        viewModel.getMovies()

        return root
    }

    private fun setupRecycleView() = with(binding) {
        rvMovie.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun updateAdapter(list: List<MovieResult>) {

        val adapter = MovieAdapter(list)
        adapter.clickListener = {
            val fragmentManager = requireActivity().supportFragmentManager
            val descriptionFragment = DescriptionFragment.newInstance(it)
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout_home, descriptionFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.rvMovie.adapter = adapter
    }

    private fun updateSearch(list: List<MovieResult>) {
        binding.etSearchMovie.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val listFind = mutableListOf<MovieResult>()
                list.forEach {
                    if (it.title.uppercase().contains(binding.etSearchMovie.text.toString().uppercase())){
                        listFind.add(it)
                    }
                }
                updateAdapter(listFind)
                true
            } else {
                false
            }
        }
    }

    private fun observeViewModel()  {
        viewModel.descriptionDetails.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is ApiResult.Success -> {
                    updateAdapter(resource.movies)
                    updateSearch(resource.movies)
                    binding.loading.isGone
                }

                is ApiResult.ServerError -> {
                    binding.loading.isGone
                }

                is ApiResult.Loading -> {
                    binding.loading.isVisible
                }

                else -> {}
            }
        }
    }

}