package com.jyldyzferr.composemoviesapp.data.cache.sourse

import com.jyldyzferr.composemoviesapp.data.cache.models.MovieDetailsCache
import kotlinx.coroutines.flow.Flow

interface MovieCacheDataSource {

    suspend fun addNewMovie(movie: MovieDetailsCache)

    suspend fun deleteMovieById(movieId: Int)

     fun fetchAllSavedMovies(): Flow<List<MovieDetailsCache>>

     fun isMovieSavedFlow(movieId: Int): Flow<Boolean>
}