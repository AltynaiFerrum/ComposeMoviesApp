package com.jyldyzferr.composemoviesapp.presentation.navigation

import BottomBarNavigationDestinations
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jyldyzferr.composemoviesapp.presentation.theme.Background
import com.jyldyzferr.composemoviesapp.presentation.theme.BottomBack
import com.jyldyzferr.composemoviesapp.presentation.theme.MyWhite

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        BottomBarNavigationDestinations.Main,
        BottomBarNavigationDestinations.Search,
        BottomBarNavigationDestinations.WatchList,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Background
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarNavigationDestinations,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = { Text(text = screen.title) },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = null
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = BottomBack,
            unselectedIconColor = Color.Gray,
            selectedTextColor = MyWhite,
            unselectedTextColor = Color.Gray,
            indicatorColor = Color.Black
        )
    )
}

