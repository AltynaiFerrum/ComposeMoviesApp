package com.jyldyzferr.composemoviesapp.data.cloud.models.reviews

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ReviewsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<ReviewsCloud>,
): Parcelable
