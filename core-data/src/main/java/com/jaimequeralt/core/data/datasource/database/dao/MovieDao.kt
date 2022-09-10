package com.jaimequeralt.core.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jaimequeralt.core.data.datasource.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieentity")
    fun getAll(): Flow<List<MovieEntity>>

    @Insert
    fun insertAll(entities: List<MovieEntity>)

    @Delete
    fun delete(movie: MovieEntity)
}