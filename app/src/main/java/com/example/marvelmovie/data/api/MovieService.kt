package com.example.marvelmovie.data.api

import com.example.marvelmovie.data.model.ApiResult
import com.example.marvelmovie.data.model.MovieResult
import retrofit2.http.GET

interface MovieService {
    @GET("saga")
    suspend fun returnMovies(): List<MovieResult>
}
