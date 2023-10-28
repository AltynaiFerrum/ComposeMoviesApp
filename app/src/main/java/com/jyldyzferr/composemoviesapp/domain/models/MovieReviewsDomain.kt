package com.jyldyzferr.composemoviesapp.domain.models

data class MovieReviewsDomain(
    val id: Int,
    val results: List<ReviewsDomain>,
)