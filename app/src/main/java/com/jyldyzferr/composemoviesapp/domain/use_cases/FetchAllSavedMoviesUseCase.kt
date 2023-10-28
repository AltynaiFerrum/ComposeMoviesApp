package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain
import kotlinx.coroutines.flow.Flow

interface FetchAllSavedMoviesUseCase {
    operator fun invoke(): Flow<List<MovieDetailsDomain>>

}