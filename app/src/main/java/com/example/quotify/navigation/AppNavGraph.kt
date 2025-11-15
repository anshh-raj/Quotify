package com.example.quotify.navigation

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quotify.DataManager
import com.example.quotify.Pages
import com.example.quotify.data.Quote
import com.example.quotify.data.QuoteCategory
import com.example.quotify.models.QuoteItem
import com.example.quotify.screens.ExploreScreen
import com.example.quotify.screens.QuoteDetail
import com.example.quotify.screens.QuoteListItem
import com.example.quotify.screens.QuoteListScreen

@Composable
fun AppNavGraph(){

    val navController = rememberNavController()

    NavHost(navController, startDestination = QuotesScreen.Home.route) {

        composable(QuotesScreen.Home.route) {

            QuoteListScreen(Quote.getQuotes(), onNavigateToExplore = {
                    val category = "motivation"
                    navController.navigate("${QuotesScreen.Explore.route}/$category")

            })
        }

        composable(
            "${QuotesScreen.Explore.route}/{categoryID}",
            arguments = listOf(navArgument("categoryID"){
                    type = NavType.StringType
                    nullable = true
                }),
        ) { backstackEntry ->

            val category = backstackEntry.arguments?.getString("categoryID")
            ExploreScreen()
        }

        composable(QuotesScreen.Saved.route) {

        }
    }
}