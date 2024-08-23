package com.example.marvelmovie.data.repository

import com.example.marvelmovie.data.api.Retrofit.service
import com.example.marvelmovie.data.model.MovieResult
import com.example.marvelmovie.domain.repository.MoviesRepository

class MoviesRepositoryImpl : MoviesRepository {

    override suspend fun getMovies(): List<MovieResult> =
        service.returnMovies()
    }
