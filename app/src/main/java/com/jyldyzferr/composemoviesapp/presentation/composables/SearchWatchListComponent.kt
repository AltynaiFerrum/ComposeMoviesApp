package com.jyldyzferr.composemoviesapp.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jyldyzferr.composemoviesapp.R
import com.jyldyzferr.composemoviesapp.presentation.theme.MyLightGray
import com.jyldyzferr.composemoviesapp.presentation.theme.MyYellow

@Composable
fun SearchWatchListComponent(
    posterUrl: String,
    movieId: Int,
    title: String,
    voteAverage: String,
    releaseDate: String,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clickable { navigateToDetails(movieId)}
    ) {
        Row(
            modifier = Modifier
                .padding(start = 20.dp, bottom = 15.dp)
        ) {
            MovieItem(
                posterPath = posterUrl,
                movieId = movieId
            )
            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row {
                    Icon(
                        Icons.Default.StarOutline,
                        contentDescription = null,
                        tint = MyYellow
                    )
                    Text(
                        modifier = Modifier,
                        text = voteAverage,
                        color = MyYellow

                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                ) {
                    Icon(
                        modifier = modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.relise_date_movie),
                        contentDescription = null,
                        tint = MyLightGray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = releaseDate,
                        style = androidx.compose.material.MaterialTheme.typography.subtitle1,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }


@Composable
fun MovieItem(
    posterPath: String,
    movieId: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(145.dp)
            .height(210.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
            model = posterPath,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }

}

