package com.jyldyzferr.composemoviesapp.data.cloud.sourse

import com.jyldyzferr.composemoviesapp.data.cloud.models.casts.ActorsResponse
import com.jyldyzferr.composemoviesapp.data.cloud.models.details.MovieDetailsResponse
import com.jyldyzferr.composemoviesapp.data.cloud.models.movie.MovieCloud
import com.jyldyzferr.composemoviesapp.data.cloud.models.reviews.ReviewsCloud
import com.jyldyzferr.composemoviesapp.data.cloud.network.MovieService

class MovieCloudDataSourceImpl(
    private val movieService: MovieService,
) : MovieCloudDataSource {
    override suspend fun fetchNowPlayingMovie(): List<MovieCloud> {
        return try {
            val movieResponse = movieService.fetchNowPlayingMovie()
            if (movieResponse.isSuccessful) {
                movieResponse.body()?.movieClouds ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Throwable) {
            emptyList()
        }

    }

    override suspend fun fetchPopularMovie(): List<MovieCloud> {
        return try {
            val movieResponse = movieService.fetchPopularMovie()
            if (movieResponse.isSuccessful) {
                movieResponse.body()?.movieClouds ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Throwable) {
            emptyList()
        }
    }

    override suspend fun fetchTopRatedMovie(): List<MovieCloud> {
        return try {
            val movieResponse = movieService.fetchTopRatedMovie()
            if (movieResponse.isSuccessful) {
                movieResponse.body()?.movieClouds ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Throwable) {
            emptyList()
        }
    }

    override suspend fun fetchUpcomingMovie(): List<MovieCloud> {
        return try {
            val movieResponse = movieService.fetchUpcomingMovie()
            if (movieResponse.isSuccessful) {
                movieResponse.body()?.movieClouds ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Throwable) {
            emptyList()
        }
    }

    override suspend fun searchByQuery(query: String): List<MovieCloud> {
        return try {
            val movieResponse = movieService.searchByQuery(query)
            if (movieResponse.isSuccessful) {
                movieResponse.body()?.movieClouds ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Throwable) {
            emptyList()
        }
    }


    override suspend fun fetchMovieById(movieId: Int): MovieDetailsResponse? {
        return try {
            val movieResponse = movieService.fetchMovieById(movieId)
            if (movieResponse.isSuccessful) {
                movieResponse.body()
            } else {
                null
            }
        } catch (e: Throwable) {
            null
        }
    }

    override suspend fun fetchMovieByIdCredits(movieId: Int): ActorsResponse {
        return try {
            val response = movieService.fetchMovieByIdCredits(movieId)
            if (response.isSuccessful && response.body() != null) {
                response.body()!!
            } else ActorsResponse.unknown
        } catch (e: Exception) {
            ActorsResponse.unknown
        }
    }

    override suspend fun fetchMovieReviews(movieId: Int): List<ReviewsCloud> {
        return try {
            val response = movieService.fetchMovieReviews(movieId)
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.results
            } else emptyList()
        } catch (e: Throwable) {
           emptyList()
        }
    }
}


