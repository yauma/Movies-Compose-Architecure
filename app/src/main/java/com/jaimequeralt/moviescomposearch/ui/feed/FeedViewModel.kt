package com.jaimequeralt.moviescomposearch.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaimequeralt.core.data.repository.PopularMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    popularMoviesRepository: PopularMoviesRepository
) :
    ViewModel() {

    val uiState: StateFlow<PopularMoviesUiState> =
        popularMoviesRepository.getPopularMoviesRepository().map {
            PopularMoviesUiState(popularMovies = it)
        }.catch {
            emit(PopularMoviesUiState(error = PopularMoviesErrorState.GenericError))
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            PopularMoviesUiState(isLoading = true)
        )
}
