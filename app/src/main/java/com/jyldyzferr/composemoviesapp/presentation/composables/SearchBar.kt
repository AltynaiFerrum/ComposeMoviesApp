import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jyldyzferr.composemoviesapp.R
import com.jyldyzferr.composemoviesapp.domain.models.MovieDomain
import com.jyldyzferr.composemoviesapp.presentation.screens.search_screen.SearchUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    navigateToSearchScreen: () -> Unit,
    topRatedMovies: List<MovieDomain>,
    navigateToDetails: (Int) -> Unit,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier
        .background(
            color = if (
                isSystemInDarkTheme()) colorResource(id = R.color.background_color)
            else colorResource(id = R.color.background_color)
        )
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = modifier
                .padding(start = 24.dp, top = 55.dp),
            text = stringResource(id = R.string.home_h1),
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )
        TextField(
            value = "",
            onValueChange = {},
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
                .clickable { navigateToSearchScreen() }
                .border(width = 0.dp, Color.DarkGray, shape = RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                containerColor = colorResource(id = R.color.search_color),
                focusedIndicatorColor = Color.Transparent,
                disabledTextColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            enabled = isEnabled
        )
        Spacer(modifier = Modifier.height(20.dp))
        MainMovieContent(
            topRatedMovies = topRatedMovies,
            navigateToDetails = navigateToDetails,
            )
    }

}

    @Composable
    fun MainMovieContent(
        topRatedMovies: List<MovieDomain>,
        navigateToDetails: (Int) -> Unit,
        ) {
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            items(items = topRatedMovies) { movie ->
                PosterFilmComponents(
                    height = 220.dp,
                    width = 160.dp,
                    movie = movie,
                    navigateToDetails = navigateToDetails,
                )
            }
        }
    }

