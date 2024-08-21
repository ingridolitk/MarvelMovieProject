package com.example.marvelmovie.data.api

import com.example.marvelmovie.model.ApiResult
import com.example.marvelmovie.model.MovieResult
import retrofit2.http.GET

interface MovieService {
    @GET("saga")
    suspend fun returnMovies(): ApiResult<List<MovieResult>>
}
