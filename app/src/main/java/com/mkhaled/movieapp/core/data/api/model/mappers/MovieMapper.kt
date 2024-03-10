package com.mkhaled.movieapp.core.data.api.model.mappers

import com.mkhaled.movieapp.core.data.api.model.ApiDiscoverMovie
import com.mkhaled.movieapp.movieScreen.domain.model.MovieUIData
import javax.inject.Inject

class MovieMapper @Inject constructor() : ApiMapper<ApiDiscoverMovie, List<MovieUIData>> {
    override fun mapToDomain(apiEntity: ApiDiscoverMovie): List<MovieUIData> {
        return apiEntity.results?.map {
            MovieUIData(
                id = it.id,
                title = it.title.orEmpty(),
                overview = it.overview.orEmpty(),
                image = it.poster_path.orEmpty(),
                releaseDate = it.release_date.orEmpty(),
                voteAverage = it.vote_average ?: Double.NaN
            )
        }.orEmpty()
    }
}