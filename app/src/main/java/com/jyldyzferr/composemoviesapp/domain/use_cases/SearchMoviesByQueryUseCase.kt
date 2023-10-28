package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.MovieDomain

interface SearchMoviesByQueryUseCase {

    suspend fun searchByQuery(query: String): List<MovieDomain>

}