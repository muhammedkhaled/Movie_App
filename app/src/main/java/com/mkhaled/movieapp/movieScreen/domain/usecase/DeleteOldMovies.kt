package com.mkhaled.movieapp.movieScreen.domain.usecase

import com.mkhaled.movieapp.core.domain.repositories.HomeRepositoryInterface
import com.mkhaled.movieapp.core.utils.Logger
import javax.inject.Inject
class DeleteOldMovies @Inject constructor(
    private val homeRepositoryInterface: HomeRepositoryInterface,
) {
    suspend operator fun invoke() {
        Logger.d("DeleteOldMovies is invoked")
        homeRepositoryInterface.deleteOldMovies()
    }
}

