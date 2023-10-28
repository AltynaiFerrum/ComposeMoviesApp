package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain
import com.jyldyzferr.composemoviesapp.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMovieByIdUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
): FetchMovieByIdUseCase{
    override suspend fun fetchMovieById(movieId: Int): MovieDetailsDomain? {
      return  repository.fetchMovieById(movieId)
    }
}
