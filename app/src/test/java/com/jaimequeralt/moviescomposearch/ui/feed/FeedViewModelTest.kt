package com.jaimequeralt.moviescomposearch.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaimequeralt.core.data.model.Movie
import com.jaimequeralt.core.data.repository.fake.FakePopularMoviesRepositoryImpl
import com.jaimequeralt.moviescomposearch.MainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FeedViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun testLazilySharingViewModel() = runTest(UnconfinedTestDispatcher()) {
        val popularMoviesRepository = FakePopularMoviesRepositoryImpl()
        val viewModel = FeedViewModel(popularMoviesRepository)

        // Create an empty collector for the StateFlow
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        // Can assert initial value
        assertEquals(
            PopularMoviesUiState(isLoading = true),
            viewModel.uiState.value
        )

        // Trigger-assert like before
        popularMoviesRepository.emit(sampleMovies)
        assertEquals(
            PopularMoviesUiState(popularMovies = sampleMovies),
             viewModel.uiState.value
        )
        collectJob.cancel()
    }
}

val sampleMovies = listOf(
    Movie(1, "Movie1", "PosterPath1"),
    Movie(2, "Movie2", "PosterPath2"),
    Movie(3, "Movie3", "PosterPath3"),
    Movie(4, "Movie4", "PosterPath4")
)


class HotFakeRepository : MyRepository {
    private val flow = MutableSharedFlow<Int>()
    suspend fun emit(value: Int) = flow.emit(value)
    override fun scores(): Flow<Int> = flow
}

interface MyRepository {
    fun scores(): Flow<Int>
}

class MyViewModelWithStateIn(myRepository: MyRepository) : ViewModel() {
    val score: StateFlow<Int> = myRepository.scores()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), 0)
}
