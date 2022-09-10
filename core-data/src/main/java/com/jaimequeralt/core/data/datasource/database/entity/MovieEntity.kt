package com.jaimequeralt.core.data.datasource.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jaimequeralt.core.data.model.Movie

@Entity
data class MovieEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "original_title") val originalTitle: String? = "",
    @ColumnInfo(name = "poster_path") val posterPath: String?
)

fun MovieEntity.asExternalModel(): Movie = Movie(id, originalTitle, posterPath)