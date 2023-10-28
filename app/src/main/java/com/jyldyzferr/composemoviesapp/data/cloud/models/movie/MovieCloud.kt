package com.jyldyzferr.composemoviesapp.data.cloud.models.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.jyldyzferr.composemoviesapp.data.cloud.models.details.MovieGenre
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCloud(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
): Parcelable

