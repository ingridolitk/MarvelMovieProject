package com.example.marvelmovie.di

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.marvelmovie.domain.usecase.MoviesUseCase
import com.example.marvelmovie.model.ApiResult
import com.example.marvelmovie.movies
import com.example.marvelmovie.presentation.movie.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var movieViewModel: MovieViewModel
    private val useCase: MoviesUseCase = mock(MoviesUseCase::class.java)
    private val observer: Observer<ApiResult> = mock(Observer::class.java) as Observer<ApiResult>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        movieViewModel = MovieViewModel(useCase)
        movieViewModel.descriptionDetails.observeForever(observer)
    }

    @Test
    fun `test getMovies success`() = testScope.runBlockingTest {

        val movies = movies
        `when`(useCase.getMarvelMovie()).thenReturn(movies)

        movieViewModel.getMovies()

        verify(observer).onChanged(ApiResult.Loading(isLoading = true))
        verify(observer).onChanged(ApiResult.Success(movies))
        verify(observer).onChanged(ApiResult.Loading(isLoading = false))
    }

    @Test
    fun `test getMovies server error`() = testScope.runBlockingTest {
        val errorMessage = "Ocorreu um erro"
        `when`(useCase.getMarvelMovie()).thenThrow(RuntimeException(errorMessage))

        movieViewModel.getMovies()

        verify(observer).onChanged(ApiResult.Loading(isLoading = true))
        verify(observer).onChanged(ApiResult.ServerError(errorMessage))
        verify(observer).onChanged(ApiResult.Loading(isLoading = false))
    }
}
