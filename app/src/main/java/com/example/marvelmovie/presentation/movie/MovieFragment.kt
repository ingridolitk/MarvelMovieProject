package com.example.marvelmovie.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelmovie.databinding.FragmentMovieBinding
import com.example.marvelmovie.model.ApiResult
import com.example.marvelmovie.model.MovieResult
import com.example.marvelmovie.presentation.adapter.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private val viewModel: MovieViewModel by viewModel()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

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

        val adapter = MovieAdapter(list,clickListener = {
            // handleClick(it)
        })
        binding.rvMovie.adapter = adapter

    }

    private fun observeViewModel()  {
        viewModel.descriptionDetails.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is ApiResult.Success -> {
                    updateAdapter(resource.movies)
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