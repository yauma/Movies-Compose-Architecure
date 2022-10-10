package com.jaimequeralt.moviescomposearch.ui.feed

import com.jaimequeralt.core.data.model.Movie

data class PopularMoviesUiState(
    val popularMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: PopularMoviesErrorState? = null
)

sealed class PopularMoviesErrorState {
    object GenericError: PopularMoviesErrorState()
}