package com.jaimequeralt.moviescomposearch.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaimequeralt.core.data.repository.PopularMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val popularMoviesRepository: PopularMoviesRepository
) :
    ViewModel() {
    init {
        viewModelScope.launch {
            popularMoviesRepository.sync()
        }
    }

    private var _moviesState =
        MutableStateFlow(PopularMoviesUiState(isLoading = true))
    val uiState: StateFlow<PopularMoviesUiState> = _moviesState.asStateFlow()

    fun getPopularMovies() {
        viewModelScope.launch {
            popularMoviesRepository.getPopularMoviesRepository().collect { movies ->
                _moviesState.update {
                    it.copy(popularMovies = movies, isLoading = false)
                }
            }
        }
    }
}
