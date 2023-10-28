import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNavigationDestinations(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Main: BottomBarNavigationDestinations(
        route = "main",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Search: BottomBarNavigationDestinations(
        route = "search",
        title = "Search",
        icon = Icons.Default.Search
    )

    object WatchList: BottomBarNavigationDestinations(
        route = "watch_list",
        title = "Watch List",
        icon = Icons.Default.Favorite
    )
}