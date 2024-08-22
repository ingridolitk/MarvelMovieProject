package com.example.marvelmovie.domain


import com.example.marvelmovie.data.api.Retrofit.service
import com.example.marvelmovie.model.MovieResult

interface MoviesRepository {
    suspend fun getMovies(): List<MovieResult>
}

class MoviesRepositoryImpl()
    : MoviesRepository {
    override suspend fun getMovies(): List<MovieResult> =
            service.returnMovies()
    }
