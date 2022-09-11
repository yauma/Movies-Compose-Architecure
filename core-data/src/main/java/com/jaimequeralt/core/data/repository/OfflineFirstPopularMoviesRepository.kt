package com.jaimequeralt.core.data.repository

import com.jaimequeralt.core.data.datasource.database.dao.MovieDao
import com.jaimequeralt.core.data.datasource.database.entity.asExternalModel
import com.jaimequeralt.core.data.datasource.network.NetworkDataSource
import com.jaimequeralt.core.data.datasource.network.model.asEntity
import com.jaimequeralt.core.data.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
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
        }

    override suspend fun sync() = withContext(IO) {
        val movies = networkMovie.getPopularMovies()
        movieDao.insertAll(movies.map {
            it.asEntity()
        })
    }
}