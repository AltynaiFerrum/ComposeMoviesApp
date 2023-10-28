package com.jyldyzferr.composemoviesapp.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jyldyzferr.composemoviesapp.domain.models.PeopleDomain

@Composable
fun CastsComponentList(
    peoples: List<PeopleDomain>,
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(65.dp)
    ){
        items(
            items = peoples
        ){people->
            CastsComponent(people)
        }
    }
}


@Composable
fun CastsComponent(
    people: PeopleDomain,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            model = people.profilePath,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = modifier.fillMaxWidth(),
            text = people.name,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}