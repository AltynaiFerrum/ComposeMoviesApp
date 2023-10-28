package com.jyldyzferr.composemoviesapp.domain.use_cases

import kotlinx.coroutines.flow.Flow

interface IsMovieSavedUseCase {
    operator fun invoke(movieId: Int) :Flow<Boolean>
}