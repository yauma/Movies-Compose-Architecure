package com.jaimequeralt.moviescomposearch.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaimequeralt.core.data.repository.PopularMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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

    val uiState = popularMoviesRepository.getPopularMoviesRepository().map {
        PopularMoviesUiState(popularMovies = it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), PopularMoviesUiState())
}
