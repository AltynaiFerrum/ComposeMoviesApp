package com.jyldyzferr.composemoviesapp.presentation.models

data class MovieCategoriesModels(
    val titleResId: String,
    val categoryType: FetchType,
) {
    companion object {
        fun getAllMovieCategories() = listOf(
            MovieCategoriesModels(
                titleResId = "Popular",
                categoryType = FetchType.POPULAR
            ),
            MovieCategoriesModels(
                titleResId = "UpComing",
                categoryType = FetchType.UPCOMING
            ),
            MovieCategoriesModels(
                titleResId = "NowPlaying",
                categoryType = FetchType.NOW_PLAYING
            ),
            MovieCategoriesModels(
                titleResId = "TopRated",
                categoryType = FetchType.TOP_RATED
            ),
        )
    }
}