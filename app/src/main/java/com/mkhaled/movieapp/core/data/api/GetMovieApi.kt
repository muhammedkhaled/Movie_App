package com.mkhaled.movieapp.core.data.api

import com.mkhaled.movieapp.core.data.api.model.ApiDiscoverMovie
import retrofit2.http.GET
import retrofit2.http.Headers

interface GetMovieApi {

    @GET("/3/discover/movie")
    suspend fun getMovie(): ApiDiscoverMovie
}