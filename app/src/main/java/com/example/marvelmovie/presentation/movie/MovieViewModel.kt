package com.example.marvelmovie.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelmovie.domain.usecase.MoviesUseCase
import com.example.marvelmovie.model.ApiResult
import com.example.marvelmovie.model.MovieResult
import kotlinx.coroutines.launch

class MovieViewModel(private val useCase: MoviesUseCase) :
    ViewModel() {
    val errorLiveData: MutableLiveData<String> = MutableLiveData()
    private val _listMovie: MutableLiveData<List<MovieResult>> = MutableLiveData()
    val listMovie: LiveData<List<MovieResult>> = _listMovie

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