package com.jaimequeralt.core.data.datasource.network.retrofit

import com.jaimequeralt.core.data.BuildConfig
import com.jaimequeralt.core.data.datasource.network.NetworkDataSource
import com.jaimequeralt.core.data.datasource.network.model.NetworkMovie
import com.squareup.moshi.Moshi
import kotlinx.serialization.Serializable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

private const val baseUrl = BuildConfig.API_URL

/**
 * Wrapper for data provided from the [baseUrl]
 */
@Serializable
private data class NetworkResponse<T>(
    val data: T
)

/**
 * [Retrofit] backed [baseUrl]
 */
@Singleton
class RetrofitNetwork @Inject constructor(private val moshi: Moshi) : NetworkDataSource {
    private val networkApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .addInterceptor { chain -> return@addInterceptor addApiKeyToRequests(chain) }
                .build()
        )
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(RetrofitNetworkApi::class.java)

    private fun addApiKeyToRequests(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY).build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }

    override suspend fun getPopularMovies(): List<NetworkMovie> =
        networkApi.getPopularMovies().results
}