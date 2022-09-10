package com.jaimequeralt.core.data

import com.jaimequeralt.core.data.repository.OfflineFirstPopularMoviesRepository
import com.jaimequeralt.core.data.repository.PopularMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsPopularMoviesRepository(
        popularMoviesRepository: OfflineFirstPopularMoviesRepository
    ): PopularMoviesRepository
}
