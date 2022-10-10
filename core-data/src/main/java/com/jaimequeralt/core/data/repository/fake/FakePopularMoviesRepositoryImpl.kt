package com.jaimequeralt.core.data.repository.fake

import com.jaimequeralt.core.data.model.Movie
import com.jaimequeralt.core.data.repository.PopularMoviesRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakePopularMoviesRepositoryImpl : PopularMoviesRepository {
    /**
     * The backing hot flow for the list of topics ids for testing.
     */
    private val moviesFlow: MutableSharedFlow<List<Movie>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override suspend fun sync() {}

    override fun getPopularMoviesRepository(): Flow<List<Movie>> = moviesFlow

    suspend fun emit(value: List<Movie>) = moviesFlow.emit(value)
}