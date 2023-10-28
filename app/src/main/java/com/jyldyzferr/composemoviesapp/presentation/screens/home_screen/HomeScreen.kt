import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.jyldyzferr.composemoviesapp.R
import com.jyldyzferr.composemoviesapp.domain.models.MovieDomain
import com.jyldyzferr.composemoviesapp.presentation.composables.ErrorMovieCard
import com.jyldyzferr.composemoviesapp.presentation.composables.HomeBoxLoading
import com.jyldyzferr.composemoviesapp.presentation.models.FetchType
import com.jyldyzferr.composemoviesapp.presentation.models.MovieCategoriesModels
import com.jyldyzferr.composemoviesapp.presentation.screens.home_screen.HomeUiState
import com.jyldyzferr.composemoviesapp.presentation.screens.shimmerEffect
import com.jyldyzferr.composemoviesapp.presentation.theme.Background
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navigateToDetails: (Int) -> Unit,
    navController: NavHostController,
    fetchMovies: () -> Unit,
    uiStateFlow: StateFlow<HomeUiState>,
    navigateToSearchScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val fullScreenModifier = modifier
        .fillMaxSize()
        .background(Background)
    when (val uiState = uiStateFlow.collectAsStateWithLifecycle().value) {
        is HomeUiState.Loading -> LoadingMainScreen(
            modifier = fullScreenModifier
        )

        is HomeUiState.Loaded -> LoadedMainScreen(
            navController = navController,
            uiState = uiState,
            navigateToDetails = navigateToDetails,
            navigateToSearchScreen = navigateToSearchScreen,
            modifier = fullScreenModifier
        )

        is HomeUiState.Error -> ErrorMainScreen(
            fetchMovies = fetchMovies,
            modifier = fullScreenModifier
        )
    }
}

@Composable
fun LoadingMainScreen(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_color))
    ) {
        Box(
            modifier
                .fillMaxSize()
                .alpha(0.5f)
                .shimmerEffect()
        )
        HomeBoxLoading()
    }
}

@Composable
fun ErrorMainScreen(
    modifier: Modifier = Modifier,
    fetchMovies: () -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        items(15) {
            ErrorMovieCard()
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun LoadedMainScreen(
    navController: NavHostController,
    navigateToDetails: (Int) -> Unit,
    uiState: HomeUiState.Loaded,
    navigateToSearchScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val movieCategory = MovieCategoriesModels.getAllMovieCategories()
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    var nowMovies = remember { uiState.popularMovie }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
    ) {
        val screenHeight = maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState)
        ) {
            Column(
                modifier = Modifier
            ) {
                SearchBar(
                    navigateToSearchScreen = navigateToSearchScreen,
                    topRatedMovies = uiState.topRatedMovie,
                    navigateToDetails = navigateToDetails,
                    isEnabled = false
                )

                Spacer(modifier = Modifier.height(12.dp))
            }
            Column(
                modifier = Modifier.height(screenHeight),
            ) {
                ScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    modifier = Modifier.height(50.dp),
                    indicator = { },
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent
                ) {
                    movieCategory.forEachIndexed { index, title ->
                        Tab(
                            text = {
                                Text(
                                    text = title.titleResId,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = if (isSystemInDarkTheme()) Color.Black
                                        else Color.White
                                    )
                                )
                            },
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                    nowMovies = when (title.categoryType) {
                                        FetchType.POPULAR -> uiState.popularMovie
                                        FetchType.NOW_PLAYING -> uiState.nowPlayingMovie
                                        FetchType.UPCOMING -> uiState.upcomingMovie
                                        FetchType.TOP_RATED -> uiState.topRatedMovie
                                    }
                                }
                            })
                    }
                }
                HorizontalPager(
                    pageCount = movieCategory.size, state = pagerState, modifier = Modifier
                        .background(Background)
                        .nestedScroll(remember {
                            object : NestedScrollConnection {
                                override fun onPreScroll(
                                    available: Offset,
                                    source: NestedScrollSource
                                ): Offset {
                                    return if (available.y > 0) Offset.Zero else Offset(
                                        x = 0f,
                                        y = -scrollState.dispatchRawDelta(-available.y)
                                    )
                                }
                            }
                        })
                )
                {
                    LazyVerticalGrid(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(all = 16.dp),
                        columns = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 40.dp),
                        userScrollEnabled = false
                    ) {
                        items(items = nowMovies) { movie ->
                            PosterFilmComponents(
                                height = 157.dp,
                                width = 112.dp,
                                movie = movie,
                                navigateToDetails = navigateToDetails,
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PosterFilmComponents(
    height: Dp,
    width: Dp,
    movie: MovieDomain,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .height(height = height)
            .width(width = width)
            .clip(RoundedCornerShape(30.dp))
            .border(1.dp, Color.Black, RoundedCornerShape(32.dp))
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            Image(
                modifier = modifier
                    .fillMaxSize()
                    .clickable { navigateToDetails(movie.id) },
                painter = rememberAsyncImagePainter(model = movie.posterPath),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }
}


