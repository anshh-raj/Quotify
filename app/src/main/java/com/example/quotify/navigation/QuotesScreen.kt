package com.example.quotify.navigation

sealed class QuotesScreen(val route: String) {

    data object Home: QuotesScreen("home")
    data object Explore: QuotesScreen("explore")

    data object Favourite: QuotesScreen("favourite")

}