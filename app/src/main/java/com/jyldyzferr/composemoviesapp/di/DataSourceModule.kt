package com.jyldyzferr.composemoviesapp.di

import com.jyldyzferr.composemoviesapp.data.cloud.network.MovieService
import com.jyldyzferr.composemoviesapp.data.cache.dao.MovieDao
import com.jyldyzferr.composemoviesapp.data.cache.sourse.MovieCacheDataSource
import com.jyldyzferr.composemoviesapp.data.cache.sourse.MovieCacheDataSourceImpl
import com.jyldyzferr.composemoviesapp.data.cloud.sourse.MovieCloudDataSource
import com.jyldyzferr.composemoviesapp.data.cloud.sourse.MovieCloudDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideMovieCacheDataSource(
        movieDao: MovieDao
    ): MovieCacheDataSource = MovieCacheDataSourceImpl(movieDao)

  @Provides
  fun provideMovieCloudDataSource(
        movieService: MovieService
    ): MovieCloudDataSource = MovieCloudDataSourceImpl(movieService)
}