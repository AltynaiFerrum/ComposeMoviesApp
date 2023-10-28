package com.jyldyzferr.composemoviesapp.domain.use_cases

import com.jyldyzferr.composemoviesapp.domain.models.MovieDomain

interface FetchMoviesIteractor {

    suspend fun fetchNowPlayingMovie(): List<MovieDomain>

    suspend fun fetchPopularMovie(): List<MovieDomain>

    suspend fun fetchTopRatedMovie(): List<MovieDomain>

    suspend fun fetchUpcomingMovie(): List<MovieDomain>
}