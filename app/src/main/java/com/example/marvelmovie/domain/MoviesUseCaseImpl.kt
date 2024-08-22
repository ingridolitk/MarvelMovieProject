package com.example.marvelmovie.domain

import com.example.marvelmovie.model.ApiResult
import com.example.marvelmovie.model.MovieResult

interface MoviesUseCase {
    suspend fun getMarvelMovie(): List<MovieResult>
}

class MoviesUseCaseImpl(
    private val movieRepository: MoviesRepository
) : MoviesUseCase {

    override suspend fun getMarvelMovie(): List<MovieResult> =
        movieRepository.getMovies()
}