package com.jyldyzferr.composemoviesapp.data.cloud.network

import com.jyldyzferr.composemoviesapp.data.cloud.models.casts.ActorsResponse
import com.jyldyzferr.composemoviesapp.data.cloud.models.details.MovieDetailsResponse
import com.jyldyzferr.composemoviesapp.data.cloud.models.movie.MovieResponse
import com.jyldyzferr.composemoviesapp.data.cloud.models.reviews.ReviewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMovie(): Response<MovieResponse>

    @GET("movie/popular")
    suspend fun fetchPopularMovie(): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovie(): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovie(): Response<MovieResponse>

    @GET("search/movie")
    suspend fun searchByQuery(
        @Query("query") query: String
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieById(
        @Path("movie_id") movieId: Int
    ): Response<MovieDetailsResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun fetchMovieByIdCredits(
        @Path("movie_id") movieId: Int
    ): Response<ActorsResponse>

      @GET("movie/{movie_id}/reviews")
    suspend fun fetchMovieReviews(
        @Path("movie_id") movieId: Int
    ): Response<ReviewsResponse>
}
