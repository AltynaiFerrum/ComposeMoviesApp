package com.jyldyzferr.composemoviesapp.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jyldyzferr.composemoviesapp.data.cache.models.MovieDetailsCache
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert
    suspend fun addNewMovie(movie: MovieDetailsCache)

    @Query("DELETE FROM movies_table WHERE id = :movieId")
    suspend fun deleteMovieById(movieId: Int)

    @Query("SELECT * FROM movies_table")
    fun fetchAllSavedMovies(): Flow<List<MovieDetailsCache>>

    @Query("SELECT EXISTS (SELECT 1 FROM movies_table WHERE id = :movieId) !=0 ")
    fun isMovieSaved(movieId: Int): Flow<Boolean>
}