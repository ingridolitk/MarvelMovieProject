package com.example.marvelmovie.model

sealed class ApiResult<out T> {
    data class Success<out T>(val movies: List<MovieResult>?, val value: T) : ApiResult<T>()
    class ServerError(val message: String? = null) : ApiResult<Nothing>()

}