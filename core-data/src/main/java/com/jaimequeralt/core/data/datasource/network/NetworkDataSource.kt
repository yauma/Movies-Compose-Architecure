package com.jaimequeralt.core.data.datasource.network

import com.jaimequeralt.core.data.datasource.network.model.NetworkMovie

interface NetworkDataSource {
    suspend fun getPopularMovies(): List<NetworkMovie>
}
