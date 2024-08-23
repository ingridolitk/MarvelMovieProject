package com.example.marvelmovie.data.model

sealed class ApiResult {
    data class Success(val movies: List<MovieResult>) : ApiResult()
    data class ServerError(val message: String? = null) : ApiResult()
    data class Loading(val isLoading: Boolean) : ApiResult()

}