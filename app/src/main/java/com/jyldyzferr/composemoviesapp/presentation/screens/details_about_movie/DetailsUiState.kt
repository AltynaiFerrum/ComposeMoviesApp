package com.jyldyzferr.composemoviesapp.presentation.screens.details_about_movie

import com.jyldyzferr.composemoviesapp.domain.models.ActorsDomain
import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain

sealed interface DetailsUiState {
    object Loading : DetailsUiState

    data class Loaded(
        val movie: MovieDetailsDomain,
        val tabs: List<DetailTab>,
        val isSaved: Boolean = false
    ) : DetailsUiState

    data class Error(
        val massage: String
    ) : DetailsUiState

}
