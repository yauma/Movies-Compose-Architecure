package com.jaimequeralt.core.data.repository

import com.jaimequeralt.core.data.datasource.database.dao.MovieDao
import com.jaimequeralt.core.data.datasource.database.entity.asExternalModel
import com.jaimequeralt.core.data.datasource.network.NetworkDataSource
import com.jaimequeralt.core.data.datasource.network.model.asEntity
import com.jaimequeralt.core.data.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstPopularMoviesRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val networkMovie: NetworkDataSource
) : PopularMoviesRepository {
    override suspend fun getPopularMoviesRepository(): Flow<List<Movie>> =
        movieDao.getAll().map { movies ->
            movies.map { movie -> movie.asExternalModel() }

        }

    suspend fun sync() {
        val movies = networkMovie.getPopularMovies()
        movieDao.insertAll(movies.map {
            it.asEntity()
        })
    }
}