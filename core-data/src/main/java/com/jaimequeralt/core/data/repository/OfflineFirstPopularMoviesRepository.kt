package com.jaimequeralt.core.data.repository

import com.jaimequeralt.core.data.datasource.database.dao.MovieDao
import com.jaimequeralt.core.data.datasource.database.entity.asExternalModel
import com.jaimequeralt.core.data.datasource.network.NetworkDataSource
import com.jaimequeralt.core.data.datasource.network.model.asEntity
import com.jaimequeralt.core.data.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class OfflineFirstPopularMoviesRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val networkMovie: NetworkDataSource
) : PopularMoviesRepository {

    override fun getPopularMoviesRepository(): Flow<List<Movie>> =
        movieDao.getAll().map { movies ->
            movies.map { movie -> movie.asExternalModel() }
        }.onEach { movies ->
            if (movies.isEmpty()) {
                sync()
            }
        }

    override suspend fun sync() = withContext(IO) {
        val movies = networkMovie.getPopularMovies()
        movieDao.insertAll(movies.map {
            it.asEntity()
        })
    }
}
