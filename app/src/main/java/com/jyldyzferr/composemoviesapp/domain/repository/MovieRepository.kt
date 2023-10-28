package com.jyldyzferr.composemoviesapp.domain.repository

import com.jyldyzferr.composemoviesapp.domain.models.ActorsDomain
import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain
import com.jyldyzferr.composemoviesapp.domain.models.MovieDomain
import com.jyldyzferr.composemoviesapp.domain.models.MovieReviewsDomain
import com.jyldyzferr.composemoviesapp.domain.models.ReviewsDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun addNewMovie(movie: MovieDetailsDomain)

    suspend fun deleteMovieById(movieId: Int)

    fun fetchAllSavedMovies(): Flow<List<MovieDetailsDomain>>

    suspend fun fetchNowPlayingMovie(): List<MovieDomain>

    suspend fun fetchPopularMovie(): List<MovieDomain>

    suspend fun fetchTopRatedMovie(): List<MovieDomain>

    suspend fun fetchUpcomingMovie(): List<MovieDomain>

    suspend fun searchByQuery(query: String): List<MovieDomain>

    suspend fun fetchMovieById(movieId: Int): MovieDetailsDomain?

    fun isMovieSavedFlow(movieId: Int): Flow<Boolean>

    suspend fun fetchMovieByIdCredits(movieId: Int): ActorsDomain

    suspend fun fetchMovieReviews(movieId: Int): List<ReviewsDomain>
}








