package com.example.marvelmovie.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelmovie.domain.usecase.MoviesUseCase
import com.example.marvelmovie.model.ApiResult
import kotlinx.coroutines.launch

class MovieViewModel(private val useCase: MoviesUseCase) :
    ViewModel() {
    private val _descriptionDetails = MutableLiveData<ApiResult>()
    val descriptionDetails: LiveData<ApiResult> = _descriptionDetails

     fun getMovies() {
        ApiResult.Loading(isLoading = true)
         viewModelScope.launch {
             _descriptionDetails.value = try {
                 ApiResult.Success(useCase.getMarvelMovie())
             } catch (e: Exception) {
                 ApiResult.ServerError(e.localizedMessage)
             }
             ApiResult.Loading(isLoading = false)
         }

    }
}