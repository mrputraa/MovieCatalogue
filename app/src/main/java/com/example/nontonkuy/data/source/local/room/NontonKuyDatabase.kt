package com.example.nontonkuy.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nontonkuy.data.source.local.entity.MovieEntity
import com.example.nontonkuy.data.source.local.entity.SeriesEntity

@Database(entities = [MovieEntity::class, SeriesEntity::class],
version = 1,
exportSchema = false)
abstract class NontonKuyDatabase: RoomDatabase() {
    abstract fun dao() : Dao

    companion object {
        @Volatile
        private var INSTANCE: NontonKuyDatabase? = null

        fun getInstance(context: Context): NontonKuyDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    NontonKuyDatabase::class.java,
                    "NontonKuy.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}