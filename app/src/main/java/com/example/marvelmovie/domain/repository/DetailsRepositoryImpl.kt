package com.example.marvelmovie.domain.repository

import com.example.marvelmovie.data.model.MovieResult

interface DetailsRepository {
    suspend fun detailsRepository(id: Int): List<MovieResult>
}