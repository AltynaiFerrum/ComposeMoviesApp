package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain

interface MovieOperatorUseCase {

    suspend fun saveMovie(movie: MovieDetailsDomain)

    suspend fun removeMovie(movieId: Int)
}