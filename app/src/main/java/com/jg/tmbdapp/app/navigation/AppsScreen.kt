package com.jg.tmbdapp.app.navigation


sealed class AppsScreen(val route: String) {
    object HomeScreen : AppsScreen("home_screen")
    object DetailsScreen : AppsScreen("details_screen")
}

