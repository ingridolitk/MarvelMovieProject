package com.example.marvelmovie.presentation.descripton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.marvelmovie.R
import com.example.marvelmovie.databinding.FragmentDescriptionBinding
import com.example.marvelmovie.model.ApiResult
import com.example.marvelmovie.model.MovieResult
import com.example.marvelmovie.utils.MOVIE_RESULT
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DescriptionFragment : Fragment() {
    private val viewModel: DescriptionViewModel by viewModel()
    private lateinit var binding: FragmentDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val movieResult: MovieResult =
            (arguments?.getParcelable<MovieResult>(MOVIE_RESULT)) as MovieResult
        descriptionBinding(movieResult)
        observeViewModel()
        return root
    }

    private fun descriptionBinding(movieResult: MovieResult) {
        binding.titleMovie.text = movieResult.title
        binding.txtActor.text = movieResult.actors
        binding.txtPlot.text = movieResult.plot
        binding.txtGenre.text = movieResult.genre
        val imgMovie = binding.imgMovieDesc

        imgMovie.let {
            Glide.with(imgMovie.context).load(
                movieResult.poster
            ).into(it)
        }
    }

    private fun observeViewModel() {
        viewModel.descriptionDetails.observe(viewLifecycleOwner) { resource ->
            binding.progressBar.isVisible
            when (resource) {
                is ApiResult.Success -> {
                    viewModel.fetch()
                }

                is ApiResult.ServerError -> {
                    Timber.tag(resources.getString(R.string.message_error))
                }

                is ApiResult.Loading -> {
                    binding.progressBar.isVisible
                }

                else -> {}
            }
            binding.progressBar.isGone
        }
    }

    companion object {

        fun newInstance(movieResult: MovieResult): DescriptionFragment {
            return DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE_RESULT, movieResult)
                }
            }
        }
    }
}