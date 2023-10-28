package com.jyldyzferr.composemoviesapp.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeBoxLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(top = 55.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
        ) {
            LoadingMovieText(width = 317.dp, height = 27.dp)
            Spacer(modifier.height(20.dp))
            LoadingMovieText(width = 327.dp, height = 50.dp)
            Spacer(modifier = Modifier.height(20.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                items(15) {
                    LoadingMovieCard()
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                LoadingMovieText(width = 90.dp, height = 41.dp)
                LoadingMovieText(width = 92.dp, height = 41.dp)
                LoadingMovieText(width = 92.dp, height = 40.dp)
                LoadingMovieText(width = 92.dp, height = 40.dp)
            }
            Spacer(modifier.height(20.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                items(15) {
                    LoadingMovieCard()
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                items(15) {
                    LoadingMovieCard()
                }
            }
        }
    }
}

