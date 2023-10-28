package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain
import com.jyldyzferr.composemoviesapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieOperatorUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
): MovieOperatorUseCase {
    override suspend fun saveMovie(movie: MovieDetailsDomain) {
        repository.addNewMovie(movie)
    }

    override suspend fun removeMovie(movieId: Int) {
        repository.deleteMovieById(movieId)
    }
}