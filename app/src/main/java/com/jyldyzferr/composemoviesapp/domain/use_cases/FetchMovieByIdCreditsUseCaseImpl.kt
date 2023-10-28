package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.ActorsDomain
import com.jyldyzferr.composemoviesapp.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMovieByIdCreditsUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : FetchMovieByIdCreditsUseCase {
    override suspend fun invoke(movieId: Int): ActorsDomain {
        return movieRepository.fetchMovieByIdCredits(movieId)
    }
}