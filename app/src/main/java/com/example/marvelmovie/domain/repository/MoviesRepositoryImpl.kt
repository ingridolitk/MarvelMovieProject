package com.example.marvelmovie.domain.repository

import com.example.marvelmovie.data.model.MovieResult

interface MoviesRepository {
    suspend fun getMovies(): List<MovieResult>
}