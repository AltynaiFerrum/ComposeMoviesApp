package com.jyldyzferr.composemoviesapp.presentation.navigation


import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {

    val route: String

    val routeWithArgs: String
}

object SplashScreenDestination : Destination {
    override val route: String = "splash_screen"
    override val routeWithArgs: String = route
}

object MainScreenDestination : Destination {
    override val route: String = "main_screen"
    override val routeWithArgs: String = route
}

object DetailsScreenDestination : Destination {
    val movieIdKey = "movieIdKey"
    override val route: String = "detail_screen"
    override val routeWithArgs: String = "$route/{$movieIdKey}"
    val arguments = listOf(navArgument(movieIdKey){ type= NavType.IntType})

}


