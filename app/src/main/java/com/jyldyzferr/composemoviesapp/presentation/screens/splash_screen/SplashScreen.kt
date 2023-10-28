package com.jyldyzferr.composemoviesapp.presentation.screens.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jyldyzferr.composemoviesapp.R
import com.jyldyzferr.composemoviesapp.presentation.navigation.MainScreenDestination
import com.jyldyzferr.composemoviesapp.presentation.navigation.SplashScreenDestination
import com.jyldyzferr.composemoviesapp.presentation.theme.ComposeMoviesAppTheme
import kotlinx.coroutines.delay

private const val SPLASH_SCREEN_SHOW_TIME = 300L

@Preview
@Composable
fun SplashScreenPreview() {
    MaterialTheme {
        ComposeMoviesAppTheme {
            SplashScreen(rememberNavController())
        }

    }
}

@Composable
fun SplashScreen(
    navController: NavHostController,
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        delay(SPLASH_SCREEN_SHOW_TIME)
        navController.navigate(MainScreenDestination.route) {
            popUpTo(route = SplashScreenDestination.route) {
                inclusive = true
            }
        }
    }
    Splash()
}

@Composable
fun Splash(
    modifier: Modifier = Modifier,

    ) {
    Box(
        modifier = modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
            .fillMaxSize(),

        contentAlignment = Alignment.Center
    ) {
        Image(

            modifier = modifier
                .height(195.dp)
                .width(195.dp),
            painter = painterResource(id = R.drawable.ic_logo_movies),
            contentDescription = null
        )
    }
}


