package com.jaimequeralt.core.data.repository

import app.cash.turbine.test
import com.jaimequeralt.core.data.testdoubles.TestMovieDao
import com.jaimequeralt.core.data.testdoubles.TestNetworkMovie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class OfflineFirstPopularMoviesRepositoryTest {
    private lateinit var subject: OfflineFirstPopularMoviesRepository
    private lateinit var movieDao: TestMovieDao
    private lateinit var network: TestNetworkMovie

    @Before
    fun setup() {
        movieDao = TestMovieDao()
        network = TestNetworkMovie()
        subject = OfflineFirstPopularMoviesRepository(movieDao, network)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun whenMovieDaoIsEmptySyncWithNetwork() = runTest(StandardTestDispatcher()) {
        movieDao.movieDaoIsEmpty()
        val movies = subject.getPopularMoviesRepository()
        movies.test {
            Assert.assertEquals(0, awaitItem().size)
            Assert.assertEquals(20, awaitItem().size)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun whenMovieDaoIsNotEmpty() = runTest(StandardTestDispatcher()) {
        val movies = subject.getPopularMoviesRepository()
        movies.test {
            Assert.assertEquals(2, awaitItem().size)
        }
    }
}