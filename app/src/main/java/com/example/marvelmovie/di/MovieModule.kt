package com.example.marvelmovie.di

import com.example.marvelmovie.data.api.MovieService
import com.example.marvelmovie.data.api.Retrofit
import com.example.marvelmovie.data.repository.DetailsRepositoryImpl
import com.example.marvelmovie.data.repository.MoviesRepositoryImpl
import com.example.marvelmovie.domain.repository.DetailsRepository
import com.example.marvelmovie.domain.repository.MoviesRepository
import com.example.marvelmovie.domain.usecase.MoviesUseCase
import com.example.marvelmovie.domain.usecase.MoviesUseCaseImpl
import com.example.marvelmovie.presentation.descripton.DescriptionViewModel
import com.example.marvelmovie.presentation.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MovieModule {
    val instance = module {
        fun provideServiceApi(retrofit: Retrofit): MovieService{
            return retrofit.service
        }
        single { provideServiceApi(retrofit = get()) }
        factory <MoviesRepository> { MoviesRepositoryImpl() }
        factory <MoviesUseCase> { MoviesUseCaseImpl(movieRepository = get()) }
        factory <DetailsRepository> { DetailsRepositoryImpl() }
        viewModel { MovieViewModel(useCase = get()) }
        viewModel { DescriptionViewModel(useCase = get()) }
    }

}