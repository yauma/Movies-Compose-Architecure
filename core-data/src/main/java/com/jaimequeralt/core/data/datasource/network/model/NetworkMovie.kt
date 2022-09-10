package com.jaimequeralt.core.data.datasource.network.model

import com.jaimequeralt.core.data.datasource.database.entity.MovieEntity
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovie(
    val id: Int,
    val originalTitle: String? = "",
    val posterPath: String?
)

fun NetworkMovie.asEntity(): MovieEntity = MovieEntity(
    id, originalTitle, posterPath
)
