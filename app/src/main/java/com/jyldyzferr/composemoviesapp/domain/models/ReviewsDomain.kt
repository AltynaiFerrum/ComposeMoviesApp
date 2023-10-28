package com.jyldyzferr.composemoviesapp.domain.models

import java.time.LocalDateTime

data class ReviewsDomain(
    val author: String,
    val authorDetails: ReviewsDetailsDomain,
    val content: String,
    val createdAt: LocalDateTime,
    val id: String,
    val updatedAt: String,
    val url: String
){
    companion object{
        val unknown = ReviewsDomain(
            author = "",
            authorDetails = ReviewsDetailsDomain.unknown,
            content = "",
            createdAt = LocalDateTime.now(),
            id = "1",
            updatedAt = "",
            url = ""
        )
    }
}