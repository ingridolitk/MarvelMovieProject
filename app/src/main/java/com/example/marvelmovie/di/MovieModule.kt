package com.example.marvelmovie.di

import com.example.marvelmovie.data.api.MovieService
import com.example.marvelmovie.domain.MoviesRepository
import com.example.marvelmovie.domain.MoviesRepositoryImpl
import com.example.marvelmovie.domain.MoviesUseCase
import com.example.marvelmovie.domain.MoviesUseCaseImpl
import org.koin.dsl.module
import retrofit2.Retrofit

object MovieModule {
    val instance = module {
        fun provideServiceApi(retrofit: Retrofit): MovieService{
            return retrofit.create(MovieService::class.java)
        }
        single { provideServiceApi(retrofit = get()) }
        factory <MoviesRepository> { MoviesRepositoryImpl(service = get()) }
        factory <MoviesUseCase> { MoviesUseCaseImpl(movieRepository = get()) }

    }

}