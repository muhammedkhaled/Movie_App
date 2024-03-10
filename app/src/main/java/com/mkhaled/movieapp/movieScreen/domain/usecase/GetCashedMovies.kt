package com.mkhaled.movieapp.movieScreen.domain.usecase

import com.mkhaled.movieapp.core.domain.repositories.HomeRepositoryInterface
import javax.inject.Inject

class GetCashedMovies @Inject constructor(
    private val homeRepositoryInterface: HomeRepositoryInterface,
) {
    operator fun invoke() = homeRepositoryInterface.getAllSavedMovies(System.currentTimeMillis() - 4 * 60 * 60 * 1000)
}