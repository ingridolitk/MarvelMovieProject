package com.example.marvelmovie.domain

import com.example.marvelmovie.data.api.MovieService
import com.example.marvelmovie.model.ApiResult
import com.example.marvelmovie.model.MovieResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MoviesRepository {
    suspend fun getMovies(): List<MovieResult>
}

class MoviesRepositoryImpl(private val service: MovieService)
    : MoviesRepository {
    override suspend fun getMovies(): List<MovieResult> =
        withContext(Dispatchers.IO) {
            service.returnMovies()
        }
    }
