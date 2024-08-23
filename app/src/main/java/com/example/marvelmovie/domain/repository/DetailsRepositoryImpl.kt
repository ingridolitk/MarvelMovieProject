package com.example.marvelmovie.domain.repository

import com.example.marvelmovie.data.api.Retrofit.service
import com.example.marvelmovie.model.MovieResult


interface DetailsRepository {
    suspend fun detailsRepository(id: Int): List<MovieResult>
}

class DetailsRepositoryImpl() : DetailsRepository {
    override suspend fun detailsRepository(id: Int):List<MovieResult> =
        service.returnMovies()
}