package com.jyldyzferr.composemoviesapp.presentation.screens.home_screen

import com.jyldyzferr.composemoviesapp.domain.models.MovieDomain

sealed interface HomeUiState {
    object Loading : HomeUiState

    data class Loaded(
        val popularMovie: List<MovieDomain>,
        val topRatedMovie: List<MovieDomain>,
        val upcomingMovie: List<MovieDomain>,
        val nowPlayingMovie: List<MovieDomain>,
    ) : HomeUiState

    data class Error(
        val massage: String
    ) : HomeUiState

}
