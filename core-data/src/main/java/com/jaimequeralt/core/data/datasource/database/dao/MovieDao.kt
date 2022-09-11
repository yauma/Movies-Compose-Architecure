package com.jaimequeralt.core.data.datasource.database.dao

import androidx.room.*
import com.jaimequeralt.core.data.datasource.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieentity")
    fun getAll(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)  // or OnConflictStrategy.IGNORE
    fun insertAll(entities: List<MovieEntity>)

    @Delete
    fun delete(movie: MovieEntity)
}