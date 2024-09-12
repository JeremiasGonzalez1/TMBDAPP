package com.jg.tmbdapp.presentation.navigation


sealed class AppsScreen(val route: String) {
    object HomeScreen : AppsScreen("home_screen")
    object DetailsScreen : AppsScreen("details_screen")
}

