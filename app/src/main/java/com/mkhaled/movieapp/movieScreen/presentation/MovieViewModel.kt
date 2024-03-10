package com.mkhaled.movieapp.movieScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkhaled.movieapp.core.data.cash.model.CashedItemData
import com.mkhaled.movieapp.core.domain.model.Event
import com.mkhaled.movieapp.core.domain.model.NetworkException
import com.mkhaled.movieapp.core.domain.model.NetworkUnavailableException
import com.mkhaled.movieapp.core.utils.Logger
import com.mkhaled.movieapp.core.utils.createExceptionHandler
import com.mkhaled.movieapp.movieScreen.domain.model.MovieUIData
import com.mkhaled.movieapp.movieScreen.domain.usecase.GetCashedMovies
import com.mkhaled.movieapp.movieScreen.domain.usecase.GetMoviesFromApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getCashedMovies: GetCashedMovies,
    private val getMoviesFromApiUseCase: GetMoviesFromApiUseCase,
    private val compositeDisposable: CompositeDisposable,
) : ViewModel() {

    // uiState
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        subscribeToUpdates()
    }

    // onEvent
    fun onEvent(event: UiEvent) {
        when (event) {
            UiEvent.GetData -> fetchDataFromApi()
        }
    }

    private fun fetchDataFromApi() {
        _state.update { oldState ->
            oldState.copy(isLoading = true)
        }
        val errorMessage = "Failed to load data"
        val exceptionHandler =
            viewModelScope.createExceptionHandler(errorMessage) { onFailure(it) }
        viewModelScope.launch(exceptionHandler) {
            getMoviesFromApiUseCase()
            _state.update { oldState ->
                oldState.copy(isLoading = false)
            }
        }
    }

    private fun subscribeToUpdates() {
        getCashedMovies()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onNewList(it) },
                { onFailure(it) }
            )
            .addTo(compositeDisposable)
    }

    private fun onNewList(list: List<CashedItemData>) {
        if (list.isEmpty()){
            fetchDataFromApi()
        } else {
            Logger.d("Data is retrieved")
            _state.update { oldState ->
                oldState.copy(
                    isLoading = false,
                    moviesList = list.map {
                        MovieUIData(
                            id = it.id,
                            title = it.title,
                            overview = it.overview,
                            image = it.imgPath,
                            releaseDate = it.releaseDate,
                            voteAverage = it.voteAverage
                        )
                    }
                )
            }
        }
    }

    private fun onFailure(failure: Throwable) {
        when (failure) {
            is NetworkException -> _state.update { oldState ->
                oldState.copy(
                    isLoading = false,
                    failure = Event(failure)
                )
            }
            is NetworkUnavailableException -> _state.update { oldState ->
                oldState.copy(
                    isLoading = false,
                    failure = Event(failure)
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // to not cause any memory leak
        compositeDisposable.clear()
    }
}