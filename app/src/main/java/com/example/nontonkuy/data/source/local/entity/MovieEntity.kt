package com.example.nontonkuy.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movieentities")
data class MovieEntity (
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "movieId")
    val movieId: Int,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "originalTitle")
    val original_title: String,

    @ColumnInfo(name = "posterPath")
    val poster_path: String,

    @ColumnInfo(name = "voteAverage")
    val vote_average: Double,

    @ColumnInfo(name = "releaseDate")
    val release_date: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
    )