import com.example.marvelmovie.data.api.MovieService
import com.example.marvelmovie.data.api.Retrofit
import com.example.marvelmovie.domain.repository.MoviesRepository
import com.example.marvelmovie.domain.usecase.MoviesUseCase
import com.example.marvelmovie.presentation.descripton.DescriptionViewModel
import com.example.marvelmovie.presentation.movie.MovieViewModel
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class MovieModuleTest : KoinTest {

    private val mockRetrofit = mockk<Retrofit>()
    private val mockMovieService = mockk<MovieService>()
    private val mockMoviesRepository = mockk<MoviesRepository>()
    private val mockMoviesUseCase = mockk<MoviesUseCase>()

    @Before
    fun setUp() {
        startKoin {
            modules(
                module {
                    single { mockRetrofit }
                    single { mockMovieService }
                    single { mockMoviesRepository }
                    single { mockMoviesUseCase }
                    viewModel { MovieViewModel(useCase = get()) }
                    viewModel { DescriptionViewModel(useCase = get()) }
                }
            )
        }
    }

    @Test
    fun `test MovieModule dependencies`() {
        val movieService: MovieService by inject()
        val moviesRepository: MoviesRepository by inject()
        val moviesUseCase: MoviesUseCase by inject()
        val movieViewModel: MovieViewModel by inject()
        val descriptionViewModel: DescriptionViewModel by inject()

        assert(movieService == mockMovieService)
        assert(moviesRepository == mockMoviesRepository)
        assert(moviesUseCase == mockMoviesUseCase)
        assert(movieViewModel != null)
        assert(descriptionViewModel != null)
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}
