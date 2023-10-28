package com.jyldyzferr.composemoviesapp.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.jyldyzferr.composemoviesapp.R
import com.jyldyzferr.composemoviesapp.domain.models.ReviewsDomain
import com.jyldyzferr.composemoviesapp.presentation.theme.BottomBack
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ReviewsComponentList(
    reviewsFlow: StateFlow<List<ReviewsDomain>>,
    modifier: Modifier = Modifier,
    ){
    val reviews = reviewsFlow.collectAsStateWithLifecycle().value

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = reviews
        ){ item ->
            ReviewsComponent(reviews = item)
        }
    }
}

@Composable
fun ReviewsComponent(
    reviews: ReviewsDomain,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Column(
         horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = reviews.authorDetails.avatarPath,
                placeholder = painterResource(id = R.drawable.reviews_avatar),
                error = painterResource(id = R.drawable.reviews_avatar),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = reviews.authorDetails.rating.toString(),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = BottomBack
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        Column{
            Text(
                text = reviews.author,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = BottomBack
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = reviews.content,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                fontWeight = FontWeight.Light,
                )
        }
    }
}


@Preview
@Composable
fun ReviewsComponentPreview(){
    MaterialTheme {
        ReviewsComponent(
            reviews = ReviewsDomain.unknown,
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }

}


