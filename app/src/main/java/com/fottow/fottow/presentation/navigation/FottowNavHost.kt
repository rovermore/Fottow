package com.fottow.fottow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fottow.fottow.presentation.login.LoginScreen
import com.fottow.fottow.presentation.main.MainScreen
import com.fottow.fottow.presentation.splash.SplashScreen

const val SplashScreen = "SplashScreen"
const val MainScreen = "MainScreen"
const val LoginScreen = "LoginScreen"

@Composable
fun FottowNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashScreen) {
        composable(SplashScreen) { SplashScreen(navController = navController) }
        composable(MainScreen) { MainScreen(navController = navController) }
        composable(LoginScreen) { LoginScreen(navController = navController)
        }
    }
}