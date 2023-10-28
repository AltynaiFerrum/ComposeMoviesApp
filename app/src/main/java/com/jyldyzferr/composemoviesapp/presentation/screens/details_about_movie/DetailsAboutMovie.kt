import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.jyldyzferr.composemoviesapp.R
import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain
import com.jyldyzferr.composemoviesapp.presentation.composables.CastsComponentList
import com.jyldyzferr.composemoviesapp.presentation.composables.DescriptionText
import com.jyldyzferr.composemoviesapp.presentation.composables.DetailScreenComponentsLoaded
import com.jyldyzferr.composemoviesapp.presentation.composables.DetailsBoxLoading
import com.jyldyzferr.composemoviesapp.presentation.composables.ErrorMessage
import com.jyldyzferr.composemoviesapp.presentation.composables.MoviePosterImage
import com.jyldyzferr.composemoviesapp.presentation.composables.ReviewsComponentList
import com.jyldyzferr.composemoviesapp.presentation.composables.ReviewsFilterPopup
import com.jyldyzferr.composemoviesapp.presentation.screens.details_about_movie.DetailTab
import com.jyldyzferr.composemoviesapp.presentation.screens.details_about_movie.DetailsUiState
import com.jyldyzferr.composemoviesapp.presentation.screens.details_about_movie.SortByItems
import com.jyldyzferr.composemoviesapp.presentation.screens.shimmerEffect
import com.jyldyzferr.composemoviesapp.presentation.theme.Background
import com.jyldyzferr.composemoviesapp.presentation.theme.MyLightGray
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun DetailsAboutMovieScreen(
    popBackStack: () -> Unit,
    uiStateFlow: StateFlow<DetailsUiState>,
    onFilterClick: (SortByItems) -> Unit,
    fetchMovie: () -> Unit,
    addOrDeleteMovie: () -> Unit,
) {
    val fullScreenModifier = Modifier
        .statusBarsPadding()
        .background(Background)

    val uiState = uiStateFlow.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = Unit) {
        fetchMovie()
    }

    when (val uiState = uiStateFlow.collectAsStateWithLifecycle().value) {
        is DetailsUiState.Loading -> MovieDetailsLoading(
            popBackStack = popBackStack,
            addOrDeleteMovie = addOrDeleteMovie,
            modifier = fullScreenModifier
        )

        is DetailsUiState.Loaded -> {
            MovieDetailsLoaded(
                popBackStack = popBackStack,
                movie = uiState.movie,
                addOrDeleteMovie = addOrDeleteMovie,
                isSaved = uiState.isSaved,
                modifier = fullScreenModifier,
                uiState = uiState,
                onFilterClick = onFilterClick
            )
        }

        is DetailsUiState.Error -> MovieDetailsError(
            popBackStack = popBackStack,
            modifier = fullScreenModifier
        )

        else -> {}
    }
}


@Composable
fun MovieDetailsLoading(
    popBackStack: () -> Unit,
    addOrDeleteMovie: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
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
        DetailsBoxLoading()
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieDetailsLoaded(
    popBackStack: () -> Unit,
    movie: MovieDetailsDomain,
    uiState: DetailsUiState.Loaded,
    addOrDeleteMovie: () -> Unit,
    isSaved: Boolean,
    onFilterClick: (SortByItems) -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    var screenHeight: Dp

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        screenHeight = maxHeight
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = scrollState)
        ) {
            Column {
                Row(
                    modifier = modifier
                        .padding(top = 18.dp)
                        .padding(horizontal = 24.dp)
                ) {
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = { popBackStack() }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack, contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.padding(start = 130.dp))
                    Text(
                        text = stringResource(id = R.string.detail),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = { addOrDeleteMovie() }
                    ) {
                        Icon(
                            painter = painterResource(id = if (isSaved) R.drawable.save_icon else R.drawable.not_saved_icon),
                            contentDescription = null,
                            tint = if (isSystemInDarkTheme()) Color.Black
                            else Color.White
                        )
                    }
                }
                Spacer(modifier.height(10.dp))
                Box(
                    modifier = modifier
                ) {
                    movie.backdropPath?.let {
                        MoviePosterImage(
                            backdropPath = it,
                            gradientColor = MaterialTheme.colorScheme.surface,
                            movie = movie
                        )
                    }
                    AsyncImage(
                        modifier = Modifier
                            .padding(horizontal = 25.dp)
                            .align(Alignment.BottomStart)
                            .clip(RoundedCornerShape(20.dp)),
                        model = movie.posterPath,
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 240.dp)
                            .padding(start = 160.dp),
                        text = movie.originalTitle,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .padding(start = 24.dp)
                ) {
                    Icon(
                        modifier = modifier.size(18.dp),
                        painter = painterResource(id = R.drawable.relise_date_movie),
                        contentDescription = null,
                        tint = MyLightGray
                    )
                    Text(
                        text = movie.releaseDate,
                        fontSize = 17.sp,
                        style = MaterialTheme.typography.bodySmall,
                        color = MyLightGray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Divider(
                        color = MyLightGray,
                        modifier = Modifier
                            .width(1.dp)
                            .height(17.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        modifier = modifier.size(18.dp),
                        painter = painterResource(id = R.drawable.runtime_movie),
                        contentDescription = null,
                        tint = MyLightGray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${movie.runtime} Minutes",
                        fontSize = 17.sp,
                        style = MaterialTheme.typography.bodySmall,
                        color = MyLightGray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Divider(
                        color = MyLightGray,
                        modifier = Modifier
                            .width(1.dp)
                            .height(17.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        modifier = modifier.size(18.dp),
                        painter = painterResource(id = R.drawable.genre_movie),
                        contentDescription = null,
                        tint = MyLightGray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${movie.movieGenresName}",
                        fontSize = 17.sp,
                        style = MaterialTheme.typography.bodySmall,
                        color = MyLightGray
                    )
                }
            }
            Column(
                modifier = Modifier.height(screenHeight)
            ) {
                ScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = Color.Transparent,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier
                                .tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                            height = 4.dp,
                            color = MaterialTheme.colorScheme.surface
                        )
                    },
                    divider = {
                        Spacer(modifier = Modifier.height(4.dp))
                    },
                    edgePadding = 0.dp
                ) {
                    uiState.tabs.forEachIndexed { index, tab ->
                        Tab(
                            modifier = Modifier.padding(10.dp),
                            selected = index == pagerState.currentPage,
                            onClick = {
                                scope.launch { pagerState.animateScrollToPage(index) }
                            },
                        ) {
                            Text(
                                text = stringResource(id = tab.titleResId),
                                style = MaterialTheme.typography.titleLarge,
                                color = if (isSystemInDarkTheme()) Color.Black
                                else Color.White
                            )
                        }
                    }
                }
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
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
                        }),
                    pageCount = uiState.tabs.size,
                    state = pagerState
                ) { index ->
                    when (val tab = uiState.tabs[index]) {
                        is DetailTab.AboutMovie -> {
                            DescriptionText(
                                description = tab.about
                            )
                        }
                        is DetailTab.Reviewers -> {
                            var isFilterClick by remember {
                                mutableStateOf(false)
                            }
                            Column {
                                Row(
                                    modifier = Modifier.padding(horizontal = 15.dp)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.filter),
                                        style = MaterialTheme.typography.titleLarge.copy(
                                            color = if (isSystemInDarkTheme()) Color.Black
                                            else Color.White
                                        ),
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Column {
                                        Icon(
                                            modifier = Modifier.clickable {
                                                isFilterClick = !isFilterClick
                                            },
                                            painter = painterResource(id = R.drawable.filter_icon),
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.background
                                        )
                                        if (isFilterClick) ReviewsFilterPopup(onClick = { sort ->
                                            isFilterClick = false
                                            onFilterClick(sort)
                                        })
                                    }
                                }
                                ReviewsComponentList(
                                    reviewsFlow = tab.reviews
                                )
                            }
                        }
                        is DetailTab.Casts -> {
                            CastsComponentList(
                                peoples = tab.cast
                            )
                        }
                        is DetailTab.Crews -> {
                            CastsComponentList(
                                peoples = tab.crews
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieDetailsError(
    modifier: Modifier = Modifier,
    popBackStack: () -> Unit,
) {
    Box {
        ErrorMessage(
            iconSize = 50.dp,
            textSize = 30.sp,
            errorColor = MaterialTheme.colorScheme.error,
            alpha = 0.8f
        )
    }
}


