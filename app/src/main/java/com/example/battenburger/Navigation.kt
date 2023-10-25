package com.example.battenburger

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.battenburger.presentation.TransformImage
import com.example.battenburger.ui.theme.BattenburgerTheme

//@Composable
//fun Navigation() {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = Screen.TransformImageScreen.route) {
//        composable(route = Screen.TransformImageScreen.route) {
//            TransformImage(navController = navController)
//        }

//        composable(route = Screen.AttachCameraScreen.route) {
//            AttachCameraScreen(navController = navController)
//        }
//        composable(route = Screen.InsertTestScreen.route) {
//            InsertTestScreen(navController = navController)
//        }
//        composable(route = Screen.CameraScreen.route) {
//            CameraScreen(navController = navController)
//        }
//        composable(route = Screen.ResultScreen.route) {
//            ResultScreen(navController = navController)
//        }

//    }
//}