package com.example.battenburger

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.battenburger.presentation.BattenburgProcessingScreen
import com.example.battenburger.presentation.DisplayBattenburgScreen
import com.example.battenburger.presentation.SelectPhotoScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SelectPhotoScreen.route)
    {
        composable(route = Screen.SelectPhotoScreen.route) {
            SelectPhotoScreen(navController = navController)
        }
        composable(route = Screen.BattenburgProcessingScreen.route) {
            BattenburgProcessingScreen(navController = navController)
        }
        composable(route = Screen.DisplayBattenburgScreen.route) {
            DisplayBattenburgScreen(navController = navController)
        }
    }
}

