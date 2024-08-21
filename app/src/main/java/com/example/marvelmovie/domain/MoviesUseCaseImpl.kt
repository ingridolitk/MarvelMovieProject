package com.example.marvelmovie.domain

import com.example.marvelmovie.model.ApiResult
import com.example.marvelmovie.model.MovieResult

interface MoviesUseCase {
    suspend fun getMarvelMovie(): ApiResult<List<MovieResult>>
}

class MoviesUseCaseImpl(
    private val movieRepository: MoviesRepositoryImpl
) : MoviesUseCase {

    override suspend fun getMarvelMovie(): ApiResult<List<MovieResult>> =
        movieRepository.getMovies()
}