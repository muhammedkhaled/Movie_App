package com.mkhaled.movieapp.core.domain.repositories

import com.mkhaled.movieapp.core.data.cash.model.CashedItemData
import com.mkhaled.movieapp.movieScreen.domain.model.MovieUIData
import io.reactivex.Flowable

interface HomeRepositoryInterface {
    fun getAllSavedMovies(lastUpdatedTime: Long): Flowable<List<CashedItemData>>
    suspend fun storeMovie(item: CashedItemData)
    suspend fun getMoviesFromApi(): List<MovieUIData>
    suspend fun deleteOldMovies()
}