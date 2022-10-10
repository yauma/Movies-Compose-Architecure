package com.jaimequeralt.moviescomposearch.ui.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.moviescomposearch.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@Composable
fun FeedRoute(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    FeedScreen(modifier = modifier, uiState = uiState)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeedScreen(modifier: Modifier, uiState: PopularMoviesUiState) {
    if (uiState.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    if (uiState.popularMovies.isNotEmpty()) {
        HorizontalPager(count = uiState.popularMovies.size) { page ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(), content = {
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = rememberAsyncImagePainter(
                            stringResource(R.string.base_url_image) + uiState.popularMovies[page].posterPath
                        ),
                        contentDescription = null,
                        modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                    )
                }
            )
        }
    }
    uiState.error?.let {
        Text(text = stringResource(R.string.generic_error))
    }
}
