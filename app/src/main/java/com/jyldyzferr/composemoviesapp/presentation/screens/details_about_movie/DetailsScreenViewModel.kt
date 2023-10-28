package com.jyldyzferr.composemoviesapp.presentation.screens.details_about_movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jyldyzferr.composemoviesapp.domain.models.ActorsDomain
import com.jyldyzferr.composemoviesapp.domain.models.MovieReviewsDomain
import com.jyldyzferr.composemoviesapp.domain.models.ReviewsDomain
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchMovieByIdCreditsUseCase
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchMovieByIdUseCase
import com.jyldyzferr.composemoviesapp.domain.use_cases.FetchMovieReviewsUseCase
import com.jyldyzferr.composemoviesapp.domain.use_cases.IsMovieSavedUseCase
import com.jyldyzferr.composemoviesapp.domain.use_cases.MovieOperatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val fetchMovieByIdUseCase: FetchMovieByIdUseCase,
    private val isMovieSavedUseCase: IsMovieSavedUseCase,
    private val movieOperatorUseCase: MovieOperatorUseCase,
    private val fetchMovieByIdCreditsUseCase: FetchMovieByIdCreditsUseCase,
    private val fetchMovieReviewsUseCase: FetchMovieReviewsUseCase,

    ) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiStateFlow: StateFlow<DetailsUiState> = _uiStateFlow.asStateFlow()

    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        _uiStateFlow.tryEmit(DetailsUiState.Error(throwable.localizedMessage ?: ""))
    }

    private val sortByFlow = MutableStateFlow(SortByItems.BY_RATING)
    private val reviewsFlow = MutableStateFlow<List<ReviewsDomain>>(emptyList())

    init {
        sortByFlow.combine(reviewsFlow){ sortBy, reviews ->
            when(sortBy){
                SortByItems.BY_RATING -> reviews.sortedByDescending{it.authorDetails.rating}
                SortByItems.BY_RATING_DOWN -> reviews.sortedBy{it.authorDetails.rating}
                SortByItems.BY_ADDED -> reviews.sortedBy{it.createdAt}
                SortByItems.BY_ADDED_DOWN -> reviews.sortedBy{it.createdAt}
            }
        }.onEach{reviews ->
            reviewsFlow.tryEmit(reviews)
        }.launchIn(viewModelScope)
    }
    fun init(movieId: Int) {
        fetchMovieById(movieId)
    }

    fun onFilterClick(sortBy: SortByItems){
        sortByFlow.tryEmit(sortBy)
    }

    private fun fetchMovieById(id: Int) {
        viewModelScope.launch(handler + Dispatchers.IO) {
            _uiStateFlow.tryEmit(DetailsUiState.Loading)

            val movieDetails = fetchMovieByIdUseCase.fetchMovieById(id)
            val actorsDomain = fetchMovieByIdCreditsUseCase(id)
            val reviewsDomain = fetchMovieReviewsUseCase(id)
            reviewsFlow.tryEmit(reviewsDomain)
            if (movieDetails == null) {
                _uiStateFlow.tryEmit(DetailsUiState.Error("Something went wrong"))
            } else {
                _uiStateFlow.tryEmit(
                    DetailsUiState.Loaded(
                        movie = movieDetails,
                        tabs = createTabsByParams(
                            aboutMovie = movieDetails.overview,
                            actorsDomain = actorsDomain,

                        ),
                    )
                )
                checkIsMovieSaved(id)
            }
        }
    }

    private fun createTabsByParams(
        aboutMovie: String,
        actorsDomain: ActorsDomain,
    ): List<DetailTab> = listOf(
        DetailTab.AboutMovie(aboutMovie),
        DetailTab.Reviewers(reviewsFlow),
        DetailTab.Casts(actorsDomain.peopleCloud),
        DetailTab.Crews(actorsDomain.crewCloud),
        )

    fun addOrDeleteMovie(movieId: Int) {
        val uiState = _uiStateFlow.value as? DetailsUiState.Loaded ?: return
        viewModelScope.launch(Dispatchers.IO) {
            if (uiState.isSaved) {
                movieOperatorUseCase.removeMovie(movieId)
                checkIsMovieSaved(movieId)
            } else {
                movieOperatorUseCase.saveMovie(uiState.movie)
                checkIsMovieSaved(movieId)
            }
        }
    }

    fun checkIsMovieSaved(movieId: Int) {
        isMovieSavedUseCase.invoke(movieId)
            .onEach { isSaved: Boolean ->
                val uiState = _uiStateFlow.value as? DetailsUiState.Loaded ?: return@onEach
                _uiStateFlow.tryEmit(uiState.copy(isSaved = isSaved))
            }
            .launchIn(viewModelScope)
    }
}





