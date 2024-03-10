package com.mkhaled.movieapp.core.data.cash.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie")
data class CashedItemData(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val overview: String,
    val imgPath: String,
    @ColumnInfo(name = "update_time")
    val updateTime: Long,
    val releaseDate: String,
    val voteAverage: Double
)