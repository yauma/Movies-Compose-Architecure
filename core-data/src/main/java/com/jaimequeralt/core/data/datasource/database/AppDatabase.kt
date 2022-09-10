package com.jaimequeralt.core.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jaimequeralt.core.data.datasource.database.dao.MovieDao
import com.jaimequeralt.core.data.datasource.database.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}