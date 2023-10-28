package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.MovieDomain
import com.jyldyzferr.composemoviesapp.domain.repository.MovieRepository
import javax.inject.Inject

class SearchMoviesByQueryUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
): SearchMoviesByQueryUseCase {

    override suspend fun searchByQuery(query: String): List<MovieDomain> {
       return repository.searchByQuery(query)
    }
}