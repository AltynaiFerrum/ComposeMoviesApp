package com.jyldyzferr.composemoviesapp.domain.models

data class MovieDetailsDomain(
    val backdropPath: String,
    val movieGenresName: List<String>,
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)