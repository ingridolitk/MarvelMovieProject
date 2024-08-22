package com.example.marvelmovie.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelmovie.R
import com.example.marvelmovie.databinding.FragmentMovieBinding
import com.example.marvelmovie.di.MovieModule
import com.example.marvelmovie.model.ApiResult
import com.example.marvelmovie.presentation.base.BaseFragment
import org.koin.core.module.Module

class MovieFragment : BaseFragment<FragmentMovieBinding, MovieViewModel>() {
    override val viewModel: MovieViewModel
        get() = TODO("Not yet implemented")

    override val module: Module get() = MovieModule.instance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
        observeViewModel()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieBinding? {
        TODO("Not yet implemented")
    }
    private fun observeViewModel() {
        viewModel.descriptionDetails.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is ApiResult.Success -> {
                   viewModel.getMovies()
                }
                is ApiResult.ServerError -> {

                }
                is ApiResult.Loading -> {
                }
                else -> {}
            }
        }
    }

}