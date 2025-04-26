package com.fottow.fottow.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fottow.fottow.presentation.gallery.GalleryScreen
import com.fottow.fottow.presentation.identification.IdentificationScreen
import com.fottow.fottow.presentation.login.LoginScreen
import com.fottow.fottow.presentation.main.MainScreen
import com.fottow.fottow.presentation.register.RegisterScreen
import com.fottow.fottow.presentation.splash.SplashScreen
import com.fottow.fottow.presentation.viewer.ImageViewerScreen
import com.fottow.fottow.presentation.widgets.ScreenContainer
import java.net.URLDecoder
import java.net.URLEncoder

const val SplashScreen = "SplashScreen"
const val MainScreen = "MainScreen"
const val LoginScreen = "LoginScreen"
const val RegisterScreen = "RegisterScreen"
const val IdentificationScreen = "IdentificationScreen"
const val GalleryScreen = "GalleryScreen"
const val ImageViewerScreen = "ImageViewerScreen"

fun NavController.navigateToImageViewer(imageUrl: String) {
    this.navigate(
        "$ImageViewerScreen?imageUrl=${URLEncoder.encode(imageUrl, "UTF-8")}"
    )
}

@Composable
fun FottowNavHost() {

    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val noBottomBarScreens = listOf(SplashScreen, LoginScreen, RegisterScreen, "ImageViewerScreen?imageUrl={imageUrl}")


    ScreenContainer(
        bottomBar = if (currentRoute !in noBottomBarScreens) {
             {
                BottomAppBar(
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IconButton(
                            onClick = { navController.navigate(GalleryScreen) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Home, "Gallery")
                        }
                        IconButton(
                            onClick = { navController.navigate(MainScreen) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.AddCircle, "MainScreen")
                        }
                        /*IconButton(
                        onClick = { navController.navigate(MainScreen) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.Person, "MainScreen")
                    }*/
                    }
                }
            }
        } else null
    ) {
        NavHost(navController = navController, startDestination = SplashScreen) {
            composable(SplashScreen) { SplashScreen(navController = navController) }
            animatedComposable(MainScreen) { MainScreen(navController = navController) }
            animatedComposable(LoginScreen) { LoginScreen(navController = navController) }
            animatedComposable(RegisterScreen) { RegisterScreen(navController = navController)}
            animatedComposable(IdentificationScreen) { IdentificationScreen(navController = navController) }
            animatedComposable(GalleryScreen) { GalleryScreen(navController = navController)}
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

                ImageViewerScreen(imageUrl = imageUrl, navController = navController) }
        }
    }
}