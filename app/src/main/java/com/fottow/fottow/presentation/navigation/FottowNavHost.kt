package com.fottow.fottow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fottow.fottow.presentation.gallery.GalleryScreen
import com.fottow.fottow.presentation.login.LoginScreen
import com.fottow.fottow.presentation.main.MainScreen
import com.fottow.fottow.presentation.register.RegisterScreen
import com.fottow.fottow.presentation.splash.SplashScreen

const val SplashScreen = "SplashScreen"
const val MainScreen = "MainScreen"
const val LoginScreen = "LoginScreen"
const val RegisterScreen = "RegisterScreen"
const val GalleryScreen = "GalleryScreen"

@Composable
fun FottowNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashScreen) {
        composable(SplashScreen) { SplashScreen(navController = navController) }
        composable(MainScreen) { MainScreen(navController = navController) }
        composable(LoginScreen) { LoginScreen(navController = navController) }
        composable(RegisterScreen) { RegisterScreen(navController = navController)}
        composable(GalleryScreen) { GalleryScreen(navController = navController)}
    }
}