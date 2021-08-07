package me.randheer.covidstatsin.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import me.randheer.covidstatsin.android.details.DistrictListScreen
import me.randheer.covidstatsin.android.details.DistrictListScreenProps
import me.randheer.covidstatsin.android.navigation.Screens
import me.randheer.covidstatsin.android.states.StateListScreen

@ExperimentalComposeUiApi
@Composable
fun ApplicationContent() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screens.StateList.route) {
        composable(route = Screens.StateList.route) {
            StateListScreen(navController = navController)
        }
        composable(
            route = Screens.DistrictList.route,
            arguments = listOf(navArgument(DistrictListScreenProps.StateCode) {
                type = NavType.StringType
            })
        ) {
            DistrictListScreen(
                navController = navController,
                stateCode = requireNotNull(it.arguments?.getString(DistrictListScreenProps.StateCode))
            )
        }
    }
}

