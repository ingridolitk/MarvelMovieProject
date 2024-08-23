package com.example.marvelmovie.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelmovie.domain.usecase.MoviesUseCase
import com.example.marvelmovie.data.model.ApiResult
import com.example.marvelmovie.data.model.MovieResult
import kotlinx.coroutines.launch

class MovieViewModel(private val useCase: MoviesUseCase) :
    ViewModel() {
    private val _descriptionDetails = MutableLiveData<ApiResult>()
    val descriptionDetails: LiveData<ApiResult> = _descriptionDetails

    fun getMovies() {
        _descriptionDetails.value = ApiResult.Loading(isLoading = true)
        viewModelScope.launch {
            try {
                val result = useCase.getMarvelMovie()
                _descriptionDetails.value = ApiResult.Success(result)
            } catch (e: Exception) {
                _descriptionDetails.value = ApiResult.ServerError(e.localizedMessage)
            } finally {
                _descriptionDetails.value = ApiResult.Loading(isLoading = false)
            }
        }
    }

     fun findSearch(list: List<MovieResult>, writeText: String): List<MovieResult> {
         val query = writeText.uppercase()
         return list.filter { it.title.uppercase().contains(query) }
    }
}