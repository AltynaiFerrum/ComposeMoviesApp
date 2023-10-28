package com.jyldyzferr.composemoviesapp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jyldyzferr.composemoviesapp.presentation.screens.MainRootScreen
import com.jyldyzferr.composemoviesapp.presentation.screens.splash_screen.SplashScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = SplashScreenDestination.route
    ) {
        composable(
            route = SplashScreenDestination.route
        ) {
            SplashScreen(
                navController
            )
        }
        composable(
            route = MainScreenDestination.route
        ) {
            MainRootScreen()
        }
    }
}
