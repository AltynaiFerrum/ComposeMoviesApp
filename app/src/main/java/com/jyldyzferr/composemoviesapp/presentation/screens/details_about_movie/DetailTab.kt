package com.jyldyzferr.composemoviesapp.presentation.screens.details_about_movie

import androidx.annotation.StringRes
import com.jyldyzferr.composemoviesapp.R
import com.jyldyzferr.composemoviesapp.domain.models.MovieReviewsDomain
import com.jyldyzferr.composemoviesapp.domain.models.PeopleDomain
import com.jyldyzferr.composemoviesapp.domain.models.ReviewsDomain
import kotlinx.coroutines.flow.StateFlow

sealed class DetailTab (
    @StringRes
    val titleResId: Int
){
    data class AboutMovie(
        val about: String
    ) : DetailTab(R.string.about_movie)

    data class Reviewers(
        val reviews: StateFlow<List<ReviewsDomain>>
    ) : DetailTab(R.string.reviews)

    data class Casts(
        val cast: List<PeopleDomain>
    ) : DetailTab(R.string.casts)

    data class Crews(
        val crews: List<PeopleDomain>
    ) : DetailTab(R.string.crews)
}


