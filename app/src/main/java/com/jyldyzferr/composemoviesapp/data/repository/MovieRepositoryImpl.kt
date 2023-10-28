package com.jyldyzferr.composemoviesapp.data.repository

import com.jyldyzferr.composemoviesapp.data.cache.sourse.MovieCacheDataSource
import com.jyldyzferr.composemoviesapp.data.cloud.sourse.MovieCloudDataSource
import com.jyldyzferr.composemoviesapp.data.mappers.toCache
import com.jyldyzferr.composemoviesapp.data.mappers.toDomain
import com.jyldyzferr.composemoviesapp.domain.models.ActorsDomain
import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain
import com.jyldyzferr.composemoviesapp.domain.models.MovieDomain
import com.jyldyzferr.composemoviesapp.domain.models.ReviewsDomain
import com.jyldyzferr.composemoviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val cloudDataSource: MovieCloudDataSource,
    private val cacheDataSource: MovieCacheDataSource,
    ) : MovieRepository {

    override suspend fun addNewMovie(movie: MovieDetailsDomain) {
        cacheDataSource.addNewMovie(movie = movie.toCache())
    }

    override suspend fun deleteMovieById(movieId: Int) {
        cacheDataSource.deleteMovieById(movieId)
    }

    override  fun fetchAllSavedMovies(): Flow<List<MovieDetailsDomain>> {
        return cacheDataSource.fetchAllSavedMovies().map { it.map {it.toDomain() }}
    }

    override suspend fun fetchNowPlayingMovie(): List<MovieDomain> {
        return cloudDataSource.fetchNowPlayingMovie().map { it.toDomain() }
    }

    override suspend fun fetchPopularMovie(): List<MovieDomain> {
        return cloudDataSource.fetchPopularMovie().map { it.toDomain() }
    }

    override suspend fun fetchTopRatedMovie(): List<MovieDomain> {
        return cloudDataSource.fetchTopRatedMovie().map { it.toDomain() }
    }

    override suspend fun fetchUpcomingMovie(): List<MovieDomain> {
        return cloudDataSource.fetchUpcomingMovie().map { it.toDomain() }
    }

    override suspend fun searchByQuery(query: String): List<MovieDomain> {
        return cloudDataSource.searchByQuery(query).map { it.toDomain() }
    }

    override suspend fun fetchMovieById(movieId: Int): MovieDetailsDomain? {
        return cloudDataSource.fetchMovieById(movieId)?.toDomain()
    }

    override fun isMovieSavedFlow(movieId: Int): Flow<Boolean> {
       return cacheDataSource.isMovieSavedFlow(movieId)
    }

    override suspend fun fetchMovieByIdCredits(movieId: Int): ActorsDomain {
       return cloudDataSource.fetchMovieByIdCredits(movieId).toDomain()
    }

    override suspend fun fetchMovieReviews(movieId: Int): List<ReviewsDomain> {
       return cloudDataSource.fetchMovieReviews(movieId).map { it.toDomain() }
    }
}