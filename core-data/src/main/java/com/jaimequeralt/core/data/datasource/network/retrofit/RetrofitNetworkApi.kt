package com.jaimequeralt.core.data.datasource.network.retrofit

import com.jaimequeralt.core.data.datasource.network.model.NetworkMovie
import com.jaimequeralt.core.data.datasource.network.model.PopularMoviesResponse
import retrofit2.http.GET

interface RetrofitNetworkApi {
    @GET(value = "movie/popular")
    suspend fun getPopularMovies(): PopularMoviesResponse
}