package com.lucgu.pcstest.presentation.feature.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucgu.pcstest.presentation.feature.detail.DetailScreen
import com.lucgu.pcstest.presentation.feature.home.HomeScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.HOME_ROUTE) {
        composable(Route.HOME_ROUTE) {
            HomeScreen(
                hiltViewModel(),
                navController,
            )
        }
        composable(Route.DETAIL_ROUTE,
        ) {
            DetailScreen(
                navController
            )
        }
    }
}