package com.example.marvelmovie.presentation.descripton

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelmovie.domain.usecase.MoviesUseCase
import com.example.marvelmovie.model.ApiResult
import com.example.marvelmovie.model.MovieResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class DescriptionViewModel(private val useCase: MoviesUseCase) :
    ViewModel() {
    private val _descriptionDetails = MutableLiveData<ApiResult>()
    val descriptionDetails: LiveData<ApiResult> = _descriptionDetails

    fun fetch() = viewModelScope.launch {
        safeFetch()
    }

    private suspend fun safeFetch() {
        _descriptionDetails.value = ApiResult.Loading(true)
        try {
            useCase.getMarvelMovie()
            _descriptionDetails.value = ApiResult.Success(useCase.getMarvelMovie())

        } catch (t: Throwable) {
            when (t) {
                is IOException -> _descriptionDetails.value =
                    ApiResult.ServerError("Erro de conexão com a internet")

                else -> _descriptionDetails.value = ApiResult.ServerError("Erro de conversão")
            }
        }
    }

}