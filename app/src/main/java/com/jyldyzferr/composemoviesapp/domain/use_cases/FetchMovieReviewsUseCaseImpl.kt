package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.ReviewsDomain
import com.jyldyzferr.composemoviesapp.domain.repository.MovieRepository
import javax.inject.Inject


class FetchMovieReviewsUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : FetchMovieReviewsUseCase {
    override suspend fun invoke(movieId: Int): List<ReviewsDomain> {
        return movieRepository.fetchMovieReviews(movieId)
    }
}