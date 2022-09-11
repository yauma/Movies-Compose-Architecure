package com.jaimequeralt.core.data.datasource.network.model

data class PopularMoviesResponse(val page: Int = 1, val results: List<NetworkMovie>)