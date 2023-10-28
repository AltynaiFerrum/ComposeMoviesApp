import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jyldyzferr.composemoviesapp.presentation.navigation.DetailsScreenDestination
import com.jyldyzferr.composemoviesapp.presentation.screens.details_about_movie.DetailsScreenViewModel
import com.jyldyzferr.composemoviesapp.presentation.screens.home_screen.MoviesViewModel
import com.jyldyzferr.composemoviesapp.presentation.screens.search_screen.SearchScreen
import com.jyldyzferr.composemoviesapp.presentation.screens.search_screen.SearchViewModel
import com.jyldyzferr.composemoviesapp.presentation.screens.watch_list_screen.WatchListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarNavigationDestinations.Main.route
    ) {
        composable(route = BottomBarNavigationDestinations.Main.route) {
            val viewModel: MoviesViewModel = hiltViewModel()
            HomeScreen(
                uiStateFlow = viewModel.uiStateFlow,
                fetchMovies = viewModel::fetchAllMovies,
                navigateToSearchScreen = {
                    navController.navigate(BottomBarNavigationDestinations.Search.route)
                },
                navigateToDetails = { movieId ->
                    navController.navigate("${DetailsScreenDestination.route}/$movieId")
                },
                navController = navController
            )
        }

        composable(route = BottomBarNavigationDestinations.Search.route) {
            val viewModel: SearchViewModel = hiltViewModel()

            SearchScreen(
                uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                onValueChange = viewModel::onValueChange,
                popBackStack = navController::navigateUp,
                navigateToDetails = { movieId ->
                    navController.navigate("${DetailsScreenDestination.route}/$movieId")
                }
            )
        }
        composable(route = BottomBarNavigationDestinations.WatchList.route) {
            val viewModel: WatchListViewModel = hiltViewModel()

            WatchListScreen(
             uiStateFlow = viewModel.uiState,
                popBackStack = navController::navigateUp,
                navigateToDetails = { movieId ->
                    navController.navigate("${DetailsScreenDestination.route}/$movieId")
                }
            )
        }
        composable(
            route = DetailsScreenDestination.routeWithArgs,
            arguments = DetailsScreenDestination.arguments
        ) { navBackStackEntry ->
            val movieId = navBackStackEntry
                .arguments
                ?.getInt(DetailsScreenDestination.movieIdKey)
                ?: 0
            val viewModel: DetailsScreenViewModel = hiltViewModel()
            DetailsAboutMovieScreen(
                uiStateFlow = viewModel.uiStateFlow,
                popBackStack = navController::navigateUp,
                fetchMovie = {
                    viewModel.init(movieId)
                },
                onFilterClick = viewModel::onFilterClick,
                addOrDeleteMovie = {
                    viewModel.addOrDeleteMovie(movieId)
                },
               // casts = CastDomain()
            )
        }
    }
}






