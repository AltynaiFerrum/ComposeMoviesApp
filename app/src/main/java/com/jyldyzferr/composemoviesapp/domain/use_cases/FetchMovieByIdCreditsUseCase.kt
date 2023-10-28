package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.ActorsDomain

interface FetchMovieByIdCreditsUseCase {

    suspend operator fun invoke(movieId: Int): ActorsDomain
}