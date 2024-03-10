package com.mkhaled.movieapp.core.data.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiDiscoverMovie(
    val page: Int?,
    val results: List<ApiResult>?,
    val total_pages: Int?,
    val total_results: Int?
)

@JsonClass(generateAdapter = true)
data class ApiResult(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)