package com.mkhaled.movieapp.movieScreen.domain.model

data class MovieUIData(
    val id: Int,
    val title: String,
    val overview: String,
    val image: String,
    val releaseDate: String,
    val voteAverage: Double
)