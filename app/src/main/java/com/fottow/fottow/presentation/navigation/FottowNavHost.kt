package com.fottow.fottow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fottow.fottow.domain.photo.model.FottowImage
import com.fottow.fottow.presentation.conditions.TermsAndConditionsScreen
import com.fottow.fottow.presentation.identification.IdentificationScreen
import com.fottow.fottow.presentation.login.LoginScreen
import com.fottow.fottow.presentation.main.MainScreen
import com.fottow.fottow.presentation.onboarding.OnboardingScreen
import com.fottow.fottow.presentation.register.RegisterScreen
import com.fottow.fottow.presentation.splash.SplashScreen
import com.fottow.fottow.presentation.viewer.ImageViewerScreen
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

const val SplashScreen = "SplashScreen"
const val MainScreen = "MainScreen"
const val LoginScreen = "LoginScreen"
const val RegisterScreen = "RegisterScreen"
const val IdentificationScreen = "IdentificationScreen"
const val GalleryScreen = "GalleryScreen"
const val ImageViewerScreen = "ImageViewerScreen"
const val TermsAndConditionsScreen = "TermsAndConditionsScreen"
const val OnBoardingScreen = "OnBoardingScreen"

fun NavController.navigateToImageViewer(imageUrl: String, photos:List<FottowImage>) {
    val photosJson = URLEncoder.encode(Json.encodeToString(photos), "UTF-8")
    this.navigate(
        "$ImageViewerScreen?imageUrl=${URLEncoder.encode(imageUrl, "UTF-8")}&photos=$photosJson"
    )
}

@Composable
fun FottowNavHost(
    isSharedPhoto: Boolean = false
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashScreen) {
        composable(SplashScreen) { SplashScreen(navController = navController) }
        composable(MainScreen) { MainScreen(navController = navController, isSharedPhoto = isSharedPhoto) }
        animatedComposable(LoginScreen) { LoginScreen(navController = navController) }
        animatedComposable(RegisterScreen) { RegisterScreen(navController = navController) }
        animatedComposable(IdentificationScreen) { IdentificationScreen(navController = navController) }
        animatedComposable(OnBoardingScreen) { OnboardingScreen(navController = navController)}
        animatedComposable(
            route = "$ImageViewerScreen?imageUrl={imageUrl}&photos={photos}",
            arguments = listOf(
                navArgument("imageUrl") {
                    type = NavType.StringType
                },
                navArgument("photos") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            // Decodificar la URL
            val imageUrl = URLDecoder.decode(
                backStackEntry.arguments?.getString("imageUrl") ?: "",
                "UTF-8"
            )
            val photosJson = URLDecoder.decode(
                backStackEntry.arguments?.getString("photos") ?: "[]",
                "UTF-8"
            )
            val photos = Json.decodeFromString<List<FottowImage>>(photosJson)
            ImageViewerScreen(imageUrl = imageUrl, photos = photos, navController = navController)
        }
        animatedComposable(TermsAndConditionsScreen) {
            TermsAndConditionsScreen {
                navController.popBackStack()
            }
        }
    }
}