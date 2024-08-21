package com.example.marvelmovie.data.api

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

const val URL_MOVIE = "https://private-b34167-rvmarvel.apiary-mock.com/"

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