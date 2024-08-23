package com.example.marvelmovie.data.api

import com.example.marvelmovie.utils.URL_MOVIE
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object Retrofit {
    var service = createRetrofit()

    private fun createRetrofit(): MovieService {
        val retrofit = Retrofit.Builder()
                .baseUrl(URL_MOVIE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(MovieService::class.java)
    }
}