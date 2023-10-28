package com.jyldyzferr.composemoviesapp.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.memory.MemoryCache
import coil.request.ImageRequest
import com.jyldyzferr.composemoviesapp.R
import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain
import com.jyldyzferr.composemoviesapp.presentation.screens.shimmerEffect
import com.jyldyzferr.composemoviesapp.presentation.theme.MyOrange
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreenComponentsLoaded(
    movie: MovieDetailsDomain,
) {
    val movieList = listOf(
        movie.overview,
        movie.releaseDate,
    )
    val movieTextList = listOf(
        "Overview",
        "Reviews"
    )
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        containerColor = Color.Transparent,

        selectedTabIndex = pagerState.currentPage
    ) {
        movieTextList.forEachIndexed { index, title ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(text = title)
                }
            )
        }
    }
    HorizontalPager(
        modifier = Modifier.padding(horizontal = 17.dp),
        pageCount = movieList.size, state = pagerState
    ) { position ->
        val currentDescription = movieList[position]
        DescriptionText(description = currentDescription)
    }
}

@Composable
fun DescriptionText(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = description,
        modifier = modifier
            .padding(top = 32.dp)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge,
        color = Color.White
    )
}

@Composable
fun MoviePosterImage(
    backdropPath: String?,
    movie: MovieDetailsDomain,
    gradientColor: Color,
    modifier: Modifier = Modifier,
    ) {
    val doubleValue = movie.voteAverage
    val floatValue = doubleValue.toFloat()

    val imageLoader = LocalContext.current.imageLoader
    backdropPath?.let {
        imageLoader.diskCache?.remove(backdropPath)
        imageLoader.memoryCache?.remove(MemoryCache.Key(backdropPath))
    }
    val posterImage = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(backdropPath)
            .crossfade(true)
            .build()
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp)
    )
    {
        Box(
            modifier = modifier
        ) {
            Card(
                modifier = Modifier
                    .width(54.dp)
                    .height(24.dp)
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(25.dp))
                    .shadow(10.dp),
            ) {
                Row {
                    Icon(
                        Icons.Default.StarOutline,
                        contentDescription = null,
                        tint = MyOrange
                    )
                    Text(
                        modifier = Modifier
                            .padding(bottom = 2.dp),
                        text = floatValue.toString(),
                        color = MyOrange
                    )
                }
            }
        }
        when (posterImage.state) {
            is AsyncImagePainter.State.Success -> {
                Image(
                    painter = posterImage,
                    contentDescription = stringResource(R.string.movie_poster_content_description),
                    contentScale = ContentScale.Crop,
                    modifier = modifier.fillMaxSize()
                )
            }

            is AsyncImagePainter.State.Error -> {
                NoMoviePoster()
            }

            is AsyncImagePainter.State.Empty -> {}

            is AsyncImagePainter.State.Loading -> {
                Image(
                    painter = posterImage,
                    contentDescription = stringResource(R.string.movie_poster_content_description),
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxSize()
                        .alpha(0.5f)
                        .shimmerEffect()
                )
            }
        }
    }
}


@Composable
fun MoviePosterPathImage(
    modifier: Modifier = Modifier,
    posterPath: String?,
    gradientColor: Color
) {
    val imageLoader = LocalContext.current.imageLoader
    posterPath?.let {
        imageLoader.diskCache?.remove(posterPath)
        imageLoader.memoryCache?.remove(MemoryCache.Key(posterPath))
    }
    val posterImage = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(posterPath)
            .crossfade(true)
            .build()
    )
    Box(
        modifier = modifier
            .height(230.dp)
            .width(170.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (posterImage.state) {
            is AsyncImagePainter.State.Success -> {
                Image(
                    painter = posterImage,
                    contentDescription = stringResource(R.string.movie_poster_content_description),
                    contentScale = ContentScale.Crop,
                    modifier = modifier.fillMaxSize()
                )
            }

            is AsyncImagePainter.State.Error -> {
                NoMoviePoster()
            }

            is AsyncImagePainter.State.Empty -> {}

            is AsyncImagePainter.State.Loading -> {
                Image(
                    painter = posterImage,
                    contentDescription = stringResource(R.string.movie_poster_content_description),
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxSize()
                        .alpha(0.5f)
                        .shimmerEffect()
                )
            }
        }
    }
}

@Composable
fun NoMoviePoster(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.no_movie),
        contentDescription = stringResource(id = R.string.loading_error_content_description),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxSize()
            .alpha(0.5f)
    )
}
