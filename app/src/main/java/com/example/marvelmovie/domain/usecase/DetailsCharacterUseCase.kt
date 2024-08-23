package com.example.marvelmovie.domain.usecase

import com.example.marvelmovie.data.model.MovieResult
import com.example.marvelmovie.domain.repository.DetailsRepository


class DetailsCharacterUseCase (private val repository: DetailsRepository) {
    suspend operator fun invoke(id: Int): List<MovieResult> {
        return repository.detailsRepository(id)
    }
}