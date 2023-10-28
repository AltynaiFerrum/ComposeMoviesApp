import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jyldyzferr.composemoviesapp.R
import com.jyldyzferr.composemoviesapp.presentation.composables.SearchWatchListComponent
import com.jyldyzferr.composemoviesapp.presentation.screens.watch_list_screen.WatchListUIState
import com.jyldyzferr.composemoviesapp.presentation.theme.Background
import kotlinx.coroutines.flow.StateFlow


@Composable
fun WatchListScreen(
    uiStateFlow: StateFlow<WatchListUIState>,
    popBackStack: () -> Unit,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    val uiState = uiStateFlow.collectAsStateWithLifecycle().value
    val fullScreenModifier = modifier
        .statusBarsPadding()
        .fillMaxSize()
        .background(Background)

    Column(
        modifier = fullScreenModifier
    ) {
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = { popBackStack() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left_movies),
                    contentDescription = null,
                    tint = colorResource(id = R.color.white),
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.watch_list),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (uiState.movies.isEmpty()) {
            NoFavoriteMovieTub()
        } else {
            LazyColumn {
                items(
                    items = uiState.movies
                ) { movie ->
                    SearchWatchListComponent(
                        navigateToDetails = navigateToDetails,
                        posterUrl = movie.posterPath,
                        title = movie.title,
                        voteAverage = movie.voteAverage.toString(),
                        releaseDate = movie.releaseDate,
                        movieId = movie.id
                    )
                }
            }
        }
    }
}

@Composable
fun NoFavoriteMovieTub(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(top = 175.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.empty_folder),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.empty_folder),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = if (isSystemInDarkTheme()) Color.Black
                    else Color.White
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.empty_folder_1),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = if (isSystemInDarkTheme()) Color.Black
                    else Color.White
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}


