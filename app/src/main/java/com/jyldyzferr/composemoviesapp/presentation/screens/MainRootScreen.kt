package com.jyldyzferr.composemoviesapp.presentation.screens

import BottomNavGraph
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jyldyzferr.composemoviesapp.domain.models.MovieDetailsDomain
import com.jyldyzferr.composemoviesapp.domain.models.MovieDomain
import com.jyldyzferr.composemoviesapp.presentation.navigation.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainRootScreen(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomBar(navController)
        }
    ) {innerPaddings ->
        BottomNavGraph(
            modifier = Modifier.padding(innerPaddings),
            navController = navController,
        )
    }
}

