package com.jyldyzferr.composemoviesapp.di

import com.jyldyzferr.composemoviesapp.data.repository.MovieRepositoryImpl
import com.jyldyzferr.composemoviesapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindMovieRepository(
        implement: MovieRepositoryImpl
    ): MovieRepository
}