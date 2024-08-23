package com.example.marvelmovie.domain.usecase

import com.example.marvelmovie.domain.repository.MoviesRepository
import com.example.marvelmovie.data.model.MovieResult

interface MoviesUseCase {
    suspend fun getMarvelMovie(): List<MovieResult>
}

class MoviesUseCaseImpl(
    private val movieRepository: MoviesRepository
) : MoviesUseCase {

    override suspend fun getMarvelMovie(): List<MovieResult> =
        movieRepository.getMovies()
}