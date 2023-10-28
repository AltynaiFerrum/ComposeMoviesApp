package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.MovieDomain
import com.jyldyzferr.composemoviesapp.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMoviesInteractorImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : FetchMoviesIteractor {
    override suspend fun fetchNowPlayingMovie(): List<MovieDomain> {
      return  movieRepository.fetchNowPlayingMovie()
    }

    override suspend fun fetchPopularMovie(): List<MovieDomain> {
        return  movieRepository.fetchPopularMovie()
    }

    override suspend fun fetchTopRatedMovie(): List<MovieDomain> {
        return  movieRepository.fetchTopRatedMovie()
    }

    override suspend fun fetchUpcomingMovie(): List<MovieDomain> {
        return  movieRepository.fetchUpcomingMovie()
    }
}