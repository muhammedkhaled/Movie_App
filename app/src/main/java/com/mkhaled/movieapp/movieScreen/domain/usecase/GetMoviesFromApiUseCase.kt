package com.mkhaled.movieapp.movieScreen.domain.usecase

import com.mkhaled.movieapp.core.data.cash.model.CashedItemData
import com.mkhaled.movieapp.movieScreen.domain.model.MovieUIData
import javax.inject.Inject
import com.mkhaled.movieapp.core.domain.repositories.HomeRepositoryInterface
import com.mkhaled.movieapp.core.utils.BASE_IMAGE_URL_w500_API
import com.mkhaled.movieapp.core.utils.DispatchersProvider
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date

class GetMoviesFromApiUseCase @Inject constructor(
    private val homeRepositoryInterface: HomeRepositoryInterface,
    private val dispatchersProvider: DispatchersProvider
) {
    suspend operator fun invoke(): List<MovieUIData> {
        val response = homeRepositoryInterface.getMoviesFromApi()
        withContext(dispatchersProvider.io()) {
            response.forEach {
                homeRepositoryInterface.storeMovie(
                    CashedItemData(
                        id = it.id,
                        title = it.title,
                        overview = it.overview,
                        imgPath = BASE_IMAGE_URL_w500_API + it.image,
                        updateTime = System.currentTimeMillis(),
                        releaseDate = it.releaseDate,
                        voteAverage = it.voteAverage
                    )
                )
            }
        }
        return response
    }
}