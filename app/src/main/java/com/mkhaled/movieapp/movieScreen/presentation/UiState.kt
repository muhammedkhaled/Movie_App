package com.mkhaled.movieapp.movieScreen.presentation

//import com.mkhaled.movieapp.core.data.cash.model.CashedItemData
import com.mkhaled.movieapp.core.domain.model.Event
import com.mkhaled.movieapp.movieScreen.domain.model.MovieUIData

data class UiState(
    val isLoading: Boolean = false,
    val moviesList: List<MovieUIData> = emptyList(),
    val failure: Event<Throwable>? = null,
)