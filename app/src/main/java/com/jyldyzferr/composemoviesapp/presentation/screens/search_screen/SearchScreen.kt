package com.jyldyzferr.composemoviesapp.presentation.screens.search_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jyldyzferr.composemoviesapp.R
import com.jyldyzferr.composemoviesapp.presentation.composables.SearchWatchListComponent
import com.jyldyzferr.composemoviesapp.presentation.theme.Background

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    popBackStack: () -> Unit,
    onValueChange: (String) -> Unit,
    navigateToDetails: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_color))
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    top = 40.dp,
                )
        ) {
            TopBar(
                popBackStack = popBackStack,
            )
            SearchBar(
                uiState = uiState,
                onValueChange = onValueChange,
                navigateToDetails = navigateToDetails
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier,
            //  .size(24.dp),
            onClick = { popBackStack() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left_movies),
                contentDescription = null,
                tint = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(start = 20.dp)
            )
        }
        Text(
            text = stringResource(id = R.string.search),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
        Icon(
            painter = painterResource(id = R.drawable.top_bar_right_movies),
            contentDescription = null,
            tint = colorResource(id = R.color.white),
            modifier = Modifier
                .padding(end = 20.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onValueChange: (String) -> Unit,
    uiState: SearchUiState,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = uiState.query,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search_icon_movies),
                    contentDescription = "Search",
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .border(width = 0.dp, Color.DarkGray, shape = RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
        when {
            uiState.movies.isEmpty() -> NoResultsTub()
            uiState.isLoading -> LoadingScreen()
            else ->
                LazyColumn {
                    items(
                        items = uiState.movies
                    ) { movie ->
                        SearchWatchListComponent(
                            posterUrl = movie.posterPath,
                            title = movie.title,
                            voteAverage = movie.voteAverage.toString(),
                            releaseDate = movie.releaseDate,
                            movieId = movie.id,
                            navigateToDetails = navigateToDetails
                        )
                    }
                }
        }
    }
}


@Composable
fun NoResultsTub(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(top = 40.dp)
            .fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.no_match_found),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.no_match_found),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = if (isSystemInDarkTheme()) Color.Black
                    else Color.White
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.no_match_found_1),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = if (isSystemInDarkTheme()) Color.Black
                    else Color.White
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    )
    {
        CircularProgressIndicator()
    }
}
