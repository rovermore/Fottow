package com.fottow.fottow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fottow.fottow.presentation.gallery.GalleryScreen
import com.fottow.fottow.presentation.identification.IdentificationScreen
import com.fottow.fottow.presentation.login.LoginScreen
import com.fottow.fottow.presentation.main.MainScreen
import com.fottow.fottow.presentation.register.RegisterScreen
import com.fottow.fottow.presentation.splash.SplashScreen

const val SplashScreen = "SplashScreen"
const val MainScreen = "MainScreen"
const val LoginScreen = "LoginScreen"
const val RegisterScreen = "RegisterScreen"
const val IdentificationScreen = "IdentificationScreen"
const val GalleryScreen = "GalleryScreen"

@Composable
fun FottowNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashScreen) {
        composable(SplashScreen) { SplashScreen(navController = navController) }
        animatedComposable(MainScreen) { MainScreen(navController = navController) }
        animatedComposable(LoginScreen) { LoginScreen(navController = navController) }
        animatedComposable(RegisterScreen) { RegisterScreen(navController = navController)}
        animatedComposable(IdentificationScreen) { IdentificationScreen(navController = navController) }
        animatedComposable(GalleryScreen) { GalleryScreen(navController = navController)}
    }
}