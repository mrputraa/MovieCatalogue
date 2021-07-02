package com.example.nontonkuy.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "seriesentities")
data class SeriesEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "seriesId")
    val seriesId: Int,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "originalName")
    val original_name: String,

    @ColumnInfo(name = "posterPath")
    val poster_path: String,

    @ColumnInfo(name = "voteAverage")
    val vote_average: Double,

    @ColumnInfo(name = "numOfEps")
    val number_of_episodes: Int?,

    @ColumnInfo(name = "firstAirDate")
    val first_air_date: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)