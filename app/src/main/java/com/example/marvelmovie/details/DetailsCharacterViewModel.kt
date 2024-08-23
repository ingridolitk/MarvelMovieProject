package com.example.myapplication.details.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelmovie.domain.usecase.DetailsCharacterUseCase
import com.example.marvelmovie.model.ApiResult
import kotlinx.coroutines.launch

class DetailsCharacterViewModel(private val detailsUseCase: DetailsCharacterUseCase): ViewModel() {

    private val _descriptionDetails = MutableLiveData<ApiResult>()
    val descriptionDetails: LiveData<ApiResult> = _descriptionDetails

    private val _error: MutableLiveData<String> = MutableLiveData()
    var error: LiveData<String> = _error

    fun fetchDetailsMovies(id: Int) {
        viewModelScope.launch {
            _descriptionDetails.value = ApiResult.Loading(isLoading = true)
            _descriptionDetails.value = try {
                ApiResult.Success(detailsUseCase.invoke(id = id))
            } catch (e: Exception) {
                ApiResult.ServerError(e.localizedMessage)
            }
            _descriptionDetails.value = ApiResult.Loading(isLoading = false)
        }
    }
}