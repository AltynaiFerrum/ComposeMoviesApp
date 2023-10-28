package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.ReviewsDomain

interface FetchMovieReviewsUseCase {
    suspend operator fun invoke(movieId: Int): List<ReviewsDomain>
}