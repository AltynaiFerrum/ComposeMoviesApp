package com.jyldyzferr.composemoviesapp.di

import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchAllSavedMoviesUseCase
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchAllSavedMoviesUseCaseImpl
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchMovieByIdCreditsUseCase
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchMovieByIdCreditsUseCaseImpl
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchMovieByIdUseCase
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchMovieByIdUseCaseImpl
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchMovieReviewsUseCase
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchMovieReviewsUseCaseImpl
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchMoviesIteractor
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchMoviesInteractorImpl
import com.jyldyzferr.composemoviesapp.domain.use_cases.IsMovieSavedUseCase
import com.jyldyzferr.composemoviesapp.domain.use_cases.IsMovieSavedUseCaseImpl
import com.jyldyzferr.composemoviesapp.domain.use_cases.MovieOperatorUseCase
import com.jyldyzferr.composemoviesapp.domain.use_cases.MovieOperatorUseCaseImpl
import com.jyldyzferr.composemoviesapp.domain.use_cases.SearchMoviesByQueryUseCase
import com.jyldyzferr.composemoviesapp.domain.use_cases.SearchMoviesByQueryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCasesModule {

    @Binds
    fun bindFetchMoviesInteractor(
        implement: FetchMoviesInteractorImpl
    ) : FetchMoviesIteractor

    @Binds
    fun bindSearchMoviesByQueryUseCase(
        implement: SearchMoviesByQueryUseCaseImpl
    ) : SearchMoviesByQueryUseCase

    @Binds
    fun bindFetchMovieByIdUseCase(
        implement: FetchMovieByIdUseCaseImpl
    ) : FetchMovieByIdUseCase

    @Binds
    fun bindIsMovieSavedFlowUseCase(
        implement: IsMovieSavedUseCaseImpl
    ): IsMovieSavedUseCase

    @Binds
    fun bindMovieOperatorUseCase(
        implement: MovieOperatorUseCaseImpl
    ): MovieOperatorUseCase

    @Binds
    fun bindFetchAllSavedMoviesUseCase(
        implement: FetchAllSavedMoviesUseCaseImpl
    ): FetchAllSavedMoviesUseCase

     @Binds
    fun bindFetchMovieByIdCreditsUseCase(
        implement: FetchMovieByIdCreditsUseCaseImpl
    ): FetchMovieByIdCreditsUseCase

    @Binds
    fun bindFetchMovieReviewsUseCase(
        implement: FetchMovieReviewsUseCaseImpl
    ): FetchMovieReviewsUseCase
}