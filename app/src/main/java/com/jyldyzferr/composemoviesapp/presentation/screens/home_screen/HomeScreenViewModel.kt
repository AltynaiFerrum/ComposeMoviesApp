package com.jyldyzferr.composemoviesapp.presentation.screens.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jyldyzferr.composemoviesapp.data.cloud.network.MovieService
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchMoviesIteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchMoviesIteractor: FetchMoviesIteractor,
    private val movieService: MovieService
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiStateFlow: StateFlow<HomeUiState> = _uiStateFlow.asStateFlow()

    init {
        fetchAllMovies()
    }

    fun fetchAllMovies() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            _uiStateFlow.tryEmit(HomeUiState.Error(throwable.localizedMessage ?: ""))
        }
        viewModelScope.launch(handler + Dispatchers.IO) {
            _uiStateFlow.tryEmit(HomeUiState.Loading)
            val popularMovie = fetchMoviesIteractor.fetchPopularMovie()
            val upcomingMovie = fetchMoviesIteractor.fetchUpcomingMovie()
            val nowPlayingMovie = fetchMoviesIteractor.fetchNowPlayingMovie()
            val topRatedMovie = fetchMoviesIteractor.fetchTopRatedMovie()

            viewModelScope.launch{
                val movieResponse = movieService.fetchMovieById(290).body()
            }
            val loaded = HomeUiState.Loaded(
                popularMovie = popularMovie,
                upcomingMovie = upcomingMovie,
                nowPlayingMovie = nowPlayingMovie,
                topRatedMovie = topRatedMovie
            )
            _uiStateFlow.tryEmit(loaded)
        }
    }
}




