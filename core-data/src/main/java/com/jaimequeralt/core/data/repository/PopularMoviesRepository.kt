package com.jaimequeralt.core.data.repository

import com.jaimequeralt.core.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {
    suspend fun sync()
    fun getPopularMoviesRepository(): Flow<List<Movie>>
}