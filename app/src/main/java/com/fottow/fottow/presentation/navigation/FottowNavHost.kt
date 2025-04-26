package com.fottow.fottow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fottow.fottow.presentation.gallery.GalleryScreen
import com.fottow.fottow.presentation.identification.IdentificationScreen
import com.fottow.fottow.presentation.login.LoginScreen
import com.fottow.fottow.presentation.main.MainScreen
import com.fottow.fottow.presentation.upload.UploadScreen
import com.fottow.fottow.presentation.profile.ProfileScreen
import com.fottow.fottow.presentation.register.RegisterScreen
import com.fottow.fottow.presentation.splash.SplashScreen
import com.fottow.fottow.presentation.viewer.ImageViewerScreen
import java.net.URLDecoder
import java.net.URLEncoder

const val SplashScreen = "SplashScreen"
const val MainScreen = "MainScreen"
const val LoginScreen = "LoginScreen"
const val RegisterScreen = "RegisterScreen"
const val IdentificationScreen = "IdentificationScreen"
const val GalleryScreen = "GalleryScreen"
const val ImageViewerScreen = "ImageViewerScreen"
const val ProfileScreen = "ProfileScreen"
const val UploadScreen = "UploadScreen"

fun NavController.navigateToImageViewer(imageUrl: String) {
    this.navigate(
        "$ImageViewerScreen?imageUrl=${URLEncoder.encode(imageUrl, "UTF-8")}"
    )
}

@Composable
fun FottowNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashScreen) {
        composable(SplashScreen) { SplashScreen(navController = navController) }
        composable(MainScreen) { MainScreen(navController = navController) }
        animatedComposable(UploadScreen) { UploadScreen(navController = navController) }
        animatedComposable(LoginScreen) { LoginScreen(navController = navController) }
        animatedComposable(RegisterScreen) { RegisterScreen(navController = navController) }
        animatedComposable(IdentificationScreen) { IdentificationScreen(navController = navController) }
        animatedComposable(GalleryScreen) { GalleryScreen(navController = navController) }
        animatedComposable(
            route = "$ImageViewerScreen?imageUrl={imageUrl}",
            arguments = listOf(
                navArgument("imageUrl") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            // Decodificar la URL
            val imageUrl = URLDecoder.decode(
                backStackEntry.arguments?.getString("imageUrl") ?: "",
                "UTF-8"
            )
            ImageViewerScreen(imageUrl = imageUrl, navController = navController)
        }
        animatedComposable(ProfileScreen) { ProfileScreen(navController = navController) }

    }
}