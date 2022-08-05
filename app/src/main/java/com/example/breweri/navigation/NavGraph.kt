package com.example.breweri.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.breweri.presentation.screens.detail.DetailScreen
import com.example.breweri.presentation.screens.home.HomeScreen
import com.example.breweri.presentation.screens.splash_screen.AnimatedSplashScreen
import com.example.breweri.util.Constants.DETAIL_ARGUMENT_KEY

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) {
            DetailScreen(navController = navController)
        }
    }
}