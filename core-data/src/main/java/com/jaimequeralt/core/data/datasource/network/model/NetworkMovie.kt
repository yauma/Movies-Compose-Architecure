package com.jaimequeralt.core.data.datasource.network.model

import com.jaimequeralt.core.data.datasource.database.entity.MovieEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class NetworkMovie(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "original_title") val originalTitle: String? = "",
    @field:Json(name = "poster_path") val posterPath: String?
)

fun NetworkMovie.asEntity(): MovieEntity = MovieEntity(
    id, originalTitle, posterPath
)
