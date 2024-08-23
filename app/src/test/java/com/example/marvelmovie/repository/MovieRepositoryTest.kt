package com.example.marvelmovie.repository

import com.example.marvelmovie.domain.repository.MoviesRepository
import com.example.marvelmovie.movies
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class MoviesRepositoryTest : KoinTest {

    private val repository: MoviesRepository by inject()

    @Before
    fun setUp() {
        startKoin {
            modules(module {
                single { mockk<MoviesRepository>().apply {
                    coEvery { getMovies() } returns movies
                } }
            })
        }
    }

    @Test
    fun `test getMovies returns expected results`() = runBlocking {
        val result = repository.getMovies()

        assertEquals(movies, result)
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}
