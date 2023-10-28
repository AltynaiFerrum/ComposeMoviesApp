package com.jyldyzferr.composemoviesapp.data.cloud.models.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieResponse(
    @SerializedName("results")
    val movieClouds: List<MovieCloud>
): Parcelable