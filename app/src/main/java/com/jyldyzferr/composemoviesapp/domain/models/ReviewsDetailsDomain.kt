package com.jyldyzferr.composemoviesapp.domain.models

data class ReviewsDetailsDomain(
    val avatarPath: String,
    val name: String?,
    val rating: Double,
    val username: String?
){
    companion object{
        val unknown = ReviewsDetailsDomain(
            avatarPath = String(),
            name = "",
            rating = 1.1,
            username = ""
        )
    }
}