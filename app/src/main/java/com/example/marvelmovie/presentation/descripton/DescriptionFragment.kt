package com.example.marvelmovie.presentation.descripton

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.marvelmovie.databinding.FragmentDescriptionBinding
import com.example.marvelmovie.model.MovieResult

class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val movieResult: MovieResult = (arguments?.getParcelable<MovieResult>(MOVIE_RESULT)) as MovieResult
        binding.titleMovie.text = movieResult.title
        binding.txtActor.text = movieResult.actors
        binding.txtPlot.text = movieResult.plot
        binding.txtGenre.text = movieResult.genre
     //   Glide.get().load(url).into(binding.imgMovieDesc)

        return root

//        val intent = intent
//        val url = intent.getStringExtra("imgMovie")


    }
    private fun descriptionBinding(movieResult: MovieResult)  {
        binding.txtActor.text = movieResult.title

//        var actor = txtActor
//        var title = titleMovie
//        var plot = txtPlot
//        var genre = txtGenre
//        var imgMovie = imgMovieDesc
//
//        val value = intent.getStringExtra("actor")
//        val titleDesc = intent.getStringExtra("title")
//        val plotDesc = intent.getStringExtra("plot")
//        val genreDesc = intent.getStringExtra("genre")
//
//        actor.text = value
//        title.text = titleDesc
//        plot.text = plotDesc
//        genre.text = genreDesc
    }

    companion object {
        const val MOVIE_RESULT ="movieResult"

        fun newInstance(movieResult: MovieResult): DescriptionFragment {
            return DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE_RESULT, movieResult)
                }
            }
        }
    }

}