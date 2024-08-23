package com.example.marvelmovie.data.repository

import com.example.marvelmovie.data.api.Retrofit.service
import com.example.marvelmovie.data.model.MovieResult
import com.example.marvelmovie.domain.repository.DetailsRepository

class DetailsRepositoryImpl() : DetailsRepository {
    override suspend fun detailsRepository(id: Int):List<MovieResult> =
        service.returnMovies()
}