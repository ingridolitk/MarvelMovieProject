package com.example.marvelmovie.presentation.descripton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.marvelmovie.databinding.FragmentDescriptionBinding
import com.example.marvelmovie.model.MovieResult
import com.example.marvelmovie.utils.MOVIE_RESULT

class DescriptionFragment : Fragment() {
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