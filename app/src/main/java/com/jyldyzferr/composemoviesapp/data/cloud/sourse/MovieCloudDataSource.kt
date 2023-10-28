package com.jyldyzferr.composemoviesapp.data.cloud.sourse

import com.jyldyzferr.composemoviesapp.data.cloud.models.casts.ActorsResponse
import com.jyldyzferr.composemoviesapp.data.cloud.models.details.MovieDetailsResponse
import com.jyldyzferr.composemoviesapp.data.cloud.models.movie.MovieCloud
import com.jyldyzferr.composemoviesapp.data.cloud.models.reviews.ReviewsCloud
import com.jyldyzferr.composemoviesapp.data.cloud.models.reviews.ReviewsResponse

interface MovieCloudDataSource {

        suspend fun fetchNowPlayingMovie(): List<MovieCloud>

        suspend fun fetchPopularMovie(): List<MovieCloud>

        suspend fun fetchTopRatedMovie(): List<MovieCloud>

        suspend fun fetchUpcomingMovie(): List<MovieCloud>

        suspend fun searchByQuery(query: String): List<MovieCloud>

        suspend fun fetchMovieById(movieId: Int): MovieDetailsResponse?

        suspend fun fetchMovieByIdCredits(movieId: Int): ActorsResponse

        suspend fun fetchMovieReviews(movieId: Int): List<ReviewsCloud>
}