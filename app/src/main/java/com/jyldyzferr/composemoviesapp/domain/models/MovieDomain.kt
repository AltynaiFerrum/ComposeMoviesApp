package com.jyldyzferr.composemoviesapp.domain.models

import java.util.UUID

data class MovieDomain(
    val backdropPath: String,
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    ){
    companion object{
        val preview = MovieDomain(
            id = UUID.randomUUID().hashCode(),
            backdropPath = "",
            posterPath = "",
            releaseDate = "11.01.2001",
            title = "Spider-man",
            voteAverage = 10.0,
        )
    }
}

