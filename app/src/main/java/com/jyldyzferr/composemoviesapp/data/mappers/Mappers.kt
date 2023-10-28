package com.jyldyzferr.composemoviesapp.data.mappers

import com.jyldyzferr.composemoviesapp.data.cache.models.MovieDetailsCache
import com.jyldyzferr.composemoviesapp.data.cloud.models.casts.ActorsResponse
import com.jyldyzferr.composemoviesapp.data.cloud.models.casts.PeopleCloud
import com.jyldyzferr.composemoviesapp.data.cloud.models.details.MovieDetailsResponse
import com.jyldyzferr.composemoviesapp.data.cloud.models.movie.MovieCloud
import com.jyldyzferr.composemoviesapp.data.cloud.models.reviews.ReviewsDetailsCloud
import com.jyldyzferr.composemoviesapp.data.cloud.models.reviews.ReviewsResponse
import com.jyldyzferr.composemoviesapp.data.cloud.models.reviews.ReviewsCloud
import com.jyldyzferr.composemoviesapp.domain.models.PeopleDomain
import com.jyldyzferr.composemoviesapp.domain.models.ActorsDomain
import com.jyldyzferr.composemoviesapp.domain.models.ReviewsDetailsDomain
import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain
import com.jyldyzferr.composemoviesapp.domain.models.MovieDomain
import com.jyldyzferr.composemoviesapp.domain.models.MovieReviewsDomain
import com.jyldyzferr.composemoviesapp.domain.models.ReviewsDomain
import java.time.ZonedDateTime

const val POSTER_PATH_URL = "https://image.tmdb.org/t/p/w342"

fun MovieDetailsDomain.toCache(): MovieDetailsCache = this.run {
    MovieDetailsCache(
        id = id,
        backdropPath = backdropPath,
        movieGenresName = movieGenresName,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun MovieDetailsCache.toDomain(): MovieDetailsDomain = this.run {
    MovieDetailsDomain(
        id = id,
        backdropPath = backdropPath,
        movieGenresName = movieGenresName,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount

    )
}

fun MovieDetailsResponse.toDomain(): MovieDetailsDomain = this.run {
    MovieDetailsDomain(
        id = id,
        backdropPath = POSTER_PATH_URL + backdropPath,
        movieGenresName = movieGenres.map { it.name },
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = POSTER_PATH_URL + posterPath,
        releaseDate = releaseDate,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun MovieCloud.toDomain(): MovieDomain = this.run {
    MovieDomain(
        id = id,
        backdropPath = POSTER_PATH_URL + backdropPath ?: "",
        posterPath = POSTER_PATH_URL + posterPath ?: "",
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
    )
}

fun PeopleCloud.toDomain() = this.run {
    PeopleDomain(
        castId = castId,
        creditId = creditId,
        id = id,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = POSTER_PATH_URL + profilePath
    )
}

fun ActorsResponse.toDomain() = this.run {
    ActorsDomain(
        id = id,
        crewCloud = crewCloud.map { it.toDomain() },
        peopleCloud = peopleCloud.map { it.toDomain() }
    )
}


fun ReviewsCloud.toDomain(): ReviewsDomain = this.run {

        ReviewsDomain(
            author = author,
            content = content,
            id = id,
            url = url,
            updatedAt = updatedAt,
            createdAt = ZonedDateTime.parse(createdAt).toLocalDateTime(),
            authorDetails = ReviewsDetailsDomain(
                avatarPath = POSTER_PATH_URL + authorDetails.avatarPath,
                name = authorDetails.name,
                username = authorDetails.username,
                rating = authorDetails.rating
            )
        )
    }
