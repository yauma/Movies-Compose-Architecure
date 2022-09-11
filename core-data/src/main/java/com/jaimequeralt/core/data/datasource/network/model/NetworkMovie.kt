package com.jaimequeralt.core.data.datasource.network.model

import com.jaimequeralt.core.data.datasource.database.entity.MovieEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
data class NetworkMovie(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "originalTitle") val originalTitle: String? = "",
    @field:Json(name = "posterPath") val posterPath: String?
)

fun NetworkMovie.asEntity(): MovieEntity = MovieEntity(
    id, originalTitle, posterPath
)
