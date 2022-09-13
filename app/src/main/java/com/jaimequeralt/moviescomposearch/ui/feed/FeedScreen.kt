package com.jaimequeralt.moviescomposearch.ui.feed

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FeedRoute(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    FeedScreen(modifier = modifier, uiState = uiState)
}

@Composable
fun FeedScreen(modifier: Modifier, uiState: PopularMoviesUiState){
    if (uiState.isLoading) {
        Text(text = "Is Loading")
    }
    if (uiState.popularMovies.isNotEmpty()) {
        Text(text = "Movies size = ${uiState.popularMovies.size}")
    }
}
