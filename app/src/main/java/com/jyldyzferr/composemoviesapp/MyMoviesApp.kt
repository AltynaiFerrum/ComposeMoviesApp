package com.jyldyzferr.composemoviesapp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jyldyzferr.composemoviesapp.presentation.navigation.AppNavGraph

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MyMoviesApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    AppNavGraph(
        navController = navController,

    )
}