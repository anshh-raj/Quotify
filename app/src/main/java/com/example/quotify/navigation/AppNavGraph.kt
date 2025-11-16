package com.example.quotify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.quotify.data.Quote
import com.example.quotify.screens.ExploreScreen
import com.example.quotify.screens.FavouriteScreen
import com.example.quotify.screens.QuoteListScreen

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(navController, startDestination = QuotesScreen.Home.route) {

        composable(QuotesScreen.Home.route) {

            QuoteListScreen(Quote.getQuotes(), onNavigateToExplore = { category ->
                navController.navigate("${QuotesScreen.Explore.route}/$category")

            })
        }

        composable(
            "${QuotesScreen.Explore.route}/{categoryID}",
            arguments = listOf(navArgument("categoryID") {
                type = NavType.StringType
                nullable = true
            }),
        ) { backstackEntry ->

            val category = backstackEntry.arguments?.getString("categoryID")
            ExploreScreen(category)
        }

        composable(QuotesScreen.Favourite.route) {
            FavouriteScreen()
        }
    }
}