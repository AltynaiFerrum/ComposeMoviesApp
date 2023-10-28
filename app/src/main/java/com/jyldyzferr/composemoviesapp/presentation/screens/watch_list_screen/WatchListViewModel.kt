package com.jyldyzferr.composemoviesapp.presentation.screens.watch_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchAllSavedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class WatchListUIState(
    val movies: List<MovieDetailsDomain> = emptyList()
)

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val fetchAllSavedMoviesUseCase: FetchAllSavedMoviesUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<WatchListUIState>(WatchListUIState())
    val uiState: StateFlow<WatchListUIState> = _uiState.asStateFlow()

    init {
        fetchAllSavedMoviesUseCase()
            .onEach { movies ->
                _uiState.tryEmit(WatchListUIState(movies = movies))
            }.launchIn(viewModelScope)
    }
}