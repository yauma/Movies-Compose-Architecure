package com.jaimequeralt.core.data.datasource.network

import com.jaimequeralt.core.data.datasource.network.retrofit.RetrofitNetwork
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Binds
    fun bindsNetwork(
        network: RetrofitNetwork
    ): NetworkDataSource

    companion object {
        @Provides
        @Singleton
        fun providesNetworkJson(): Moshi =
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
    }
}