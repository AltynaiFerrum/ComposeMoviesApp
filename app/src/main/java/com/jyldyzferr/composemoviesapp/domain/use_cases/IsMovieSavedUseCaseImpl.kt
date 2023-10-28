package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsMovieSavedUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
): IsMovieSavedUseCase {
    override fun invoke(movieId: Int): Flow<Boolean> {
        return repository.isMovieSavedFlow(movieId)
    }
}