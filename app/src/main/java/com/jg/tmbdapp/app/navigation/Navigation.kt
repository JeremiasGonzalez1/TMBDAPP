package com.jg.tmbdapp.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jg.tmbdapp.app.screens.HomeScreen
@Composable
fun Navigation(modifier: Modifier = Modifier, navHostController: NavHostController) {
    NavHost(modifier = modifier, navController = navHostController, startDestination = AppsScreen.HomeScreen.route){
        composable(route = AppsScreen.HomeScreen.route){
            HomeScreen()
        }
    }
}