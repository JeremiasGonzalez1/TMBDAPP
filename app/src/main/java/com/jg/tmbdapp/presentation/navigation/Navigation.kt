package com.jg.tmbdapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jg.tmbdapp.presentation.screens.HomeScreen
import com.jg.tmbdapp.presentation.screens.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val viewModel: HomeViewModel = koinViewModel()
    NavHost(navController = navHostController, startDestination = AppsScreen.HomeScreen.route){
        composable(route = AppsScreen.HomeScreen.route){
            HomeScreen(viewModel)
        }

    }
}