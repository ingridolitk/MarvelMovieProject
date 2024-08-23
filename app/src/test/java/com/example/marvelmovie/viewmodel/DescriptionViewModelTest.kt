package com.example.marvelmovie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.marvelmovie.domain.usecase.MoviesUseCase
import com.example.marvelmovie.data.model.ApiResult
import com.example.marvelmovie.movies
import com.example.marvelmovie.presentation.descripton.DescriptionViewModel
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
class DescriptionViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var descriptionViewModel: DescriptionViewModel
    private val useCase: MoviesUseCase = mock(MoviesUseCase::class.java)
    private val observer: Observer<ApiResult> = mock(Observer::class.java) as Observer<ApiResult>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        descriptionViewModel = DescriptionViewModel(useCase)
        descriptionViewModel.descriptionDetails.observeForever(observer)
    }

    @Test
    fun `test fetch success`() = testScope.runBlockingTest {
        val movies = movies
        `when`(useCase.getMarvelMovie()).thenReturn(movies)

        descriptionViewModel.fetch()

        verify(observer).onChanged(ApiResult.Loading(true))
        verify(observer).onChanged(ApiResult.Success(movies))
    }

    @Test
    fun `test fetch generic error`() = testScope.runBlockingTest {
        `when`(useCase.getMarvelMovie()).thenThrow(RuntimeException())

        descriptionViewModel.fetch()

        verify(observer).onChanged(ApiResult.Loading(true))
        verify(observer).onChanged(ApiResult.ServerError("Erro de conversão"))
    }
}
