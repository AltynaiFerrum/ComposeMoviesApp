package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain
import com.jyldyzferr.composemoviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAllSavedMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): FetchAllSavedMoviesUseCase
{
    override fun invoke(): Flow<List<MovieDetailsDomain>> {
        return movieRepository.fetchAllSavedMovies()
    }
}