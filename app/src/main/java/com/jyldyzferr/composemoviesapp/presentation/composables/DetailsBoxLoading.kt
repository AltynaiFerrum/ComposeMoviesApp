package com.jyldyzferr.composemoviesapp.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jyldyzferr.composemoviesapp.presentation.screens.shimmerEffect

@Composable
fun DetailsBoxLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(top = 280.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 20.dp, end = 20.dp)
        ) {
            Row(
                modifier
                    .fillMaxWidth()
                    .height(80.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // User score
                Row(modifier.padding(top = 10.dp)) {
                    Card(
                        shape = CircleShape,
                        modifier = modifier.alpha(0.5f)
                    ) {
                        Box(
                            modifier
                                .size(60.dp)
                                .shimmerEffect()
                        )
                    }
                    Spacer(modifier.width(12.dp))
                    Column(modifier.padding(top = 2.dp)) {
                        LoadingMovieText(width = 40.dp, height = 22.dp)
                        Spacer(modifier.height(8.dp))
                        LoadingMovieText(width = 55.dp, height = 22.dp)
                    }
                }
            }
            Spacer(modifier.height(5.dp))

            LoadingMovieText(width = 300.dp, height = 35.dp)
            Spacer(modifier.height(15.dp))

            LoadingMovieText(width = 100.dp, height = 20.dp)
            Spacer(modifier.height(15.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                LoadingMovieText(width = 40.dp, height = 22.dp)
                LoadingMovieText(width = 80.dp, height = 22.dp)
                LoadingMovieText(width = 60.dp, height = 22.dp)
            }
            Spacer(modifier.height(15.dp))
           
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                LoadingMovieText(width = 400.dp, height = 22.dp)
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    LoadingMovieText(width = 200.dp, height = 22.dp)
                    LoadingMovieText(width = 90.dp, height = 22.dp)
                    LoadingMovieText(width = 90.dp, height = 22.dp)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    LoadingMovieText(width = 50.dp, height = 22.dp)
                    LoadingMovieText(width = 180.dp, height = 22.dp)
                    LoadingMovieText(width = 150.dp, height = 22.dp)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    LoadingMovieText(width = 150.dp, height = 22.dp)
                    LoadingMovieText(width = 100.dp, height = 22.dp)
                    LoadingMovieText(width = 120.dp, height = 22.dp)
                }
            }
        }
    }
}

@Composable
fun LoadingMovieText(
    modifier: Modifier = Modifier,
    width: Dp,
    height: Dp
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.alpha(0.5f)
    ) {
        Box(
            modifier = modifier
                .width(width)
                .height(height)
                .shimmerEffect()
        )
    }
}
