package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain

interface FetchMovieByIdUseCase{
   suspend fun  fetchMovieById(movieId: Int): MovieDetailsDomain?
}