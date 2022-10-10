package com.jaimequeralt.core.data.testdoubles

import com.jaimequeralt.core.data.datasource.database.dao.MovieDao
import com.jaimequeralt.core.data.datasource.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class TestMovieDao : MovieDao {
    private var popularMoviesStateFlow =
        MutableStateFlow(
            listOf(
                MovieEntity(1, "title", "posterPath"),
                MovieEntity(1, "title", "posterPath")
            )
        )

    override fun getAll(): Flow<List<MovieEntity>> = popularMoviesStateFlow

    override fun insertAll(entities: List<MovieEntity>) {
        popularMoviesStateFlow.value = entities
    }

    override fun delete(movie: MovieEntity) {
        popularMoviesStateFlow.update {
            it.filterNot { movieEntity ->
                movieEntity.id == movie.id
            }
        }
    }
    fun movieDaoIsEmpty() {
        popularMoviesStateFlow.value = emptyList()
    }
}