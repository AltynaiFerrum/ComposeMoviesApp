package com.jyldyzferr.composemoviesapp.domain.models


data class PeopleDomain(
    val castId: Int,
    val creditId: String,
    val id: Int,
    val name: String,
    val originalName: String?,
    val popularity: Double?,
    val profilePath: String?
)

