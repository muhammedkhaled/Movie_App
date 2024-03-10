package com.mkhaled.movieapp.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mkhaled.movieapp.detailsScreen.presentation.DetailsScreen
import com.mkhaled.movieapp.movieScreen.presentation.MovieScreen
import com.mkhaled.movieapp.movieScreen.presentation.MovieViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = hiltViewModel(),
    naveController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = naveController,
        startDestination = NavigationItem.Home.route
    ) {
        composable(NavigationItem.Home.route) {
            MovieScreen(
                state = viewModel.state.collectAsState().value,
                onEvent = { viewModel.onEvent(it) }
            ) { title, overview, image, releaseDate, voteAverage ->
                naveController.navigate("$DETAILS/$title/$overview/$image/$releaseDate/$voteAverage")
            }
        }
        composable(
            NavigationItem.Details.route,
            arguments = listOf(
                navArgument(name = "title") {
                    type = NavType.StringType
                },
                navArgument(name = "overview") {
                    type = NavType.StringType
                },
                navArgument(name = "image") {
                    type = NavType.StringType
                },
                navArgument(name = "releaseDate") {
                    type = NavType.StringType
                },
                navArgument(name = "voteAverage") {
                    type = NavType.FloatType
                },
            ),
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            val overview = backStackEntry.arguments?.getString("overview")
            val image = backStackEntry.arguments?.getString("image")
            val releaseDate = backStackEntry.arguments?.getString("releaseDate")
            val voteAverage = backStackEntry.arguments?.getFloat("voteAverage")
            DetailsScreen(
                title = title.orEmpty(),
                overview = overview.orEmpty(),
                image = image.orEmpty(),
                releaseDate = releaseDate.orEmpty(),
                voteAverage = voteAverage ?: 0f
            )
        }
    }
}


const val HOME = "home"
const val DETAILS = "details"

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(HOME)
    object Details :
        NavigationItem("$DETAILS/{title}/{overview}/{image}/{releaseDate}/{voteAverage}")
}
//val title: String,
//val overview: String,
//val imgPath: String,
//@ColumnInfo(name = "update_time")
//val updateTime: Long,
//val releaseDate: String,
//val voteAverage: Double