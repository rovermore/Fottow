package com.fottow.fottow.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.fottow.fottow.presentation.navigation.LoginScreen
import com.fottow.fottow.presentation.navigation.MainScreen
import com.fottow.fottow.presentation.theme.Purple40
import com.fottow.fottow.presentation.theme.White
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = koinViewModel<SplashScreenViewModel>(),
    navController: NavController
) {
    val isUserLoged by viewModel.isUserLogged.collectAsStateWithLifecycle()
    val isLoading by viewModel.loading.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = isUserLoged, key2 = isLoading) {
        coroutineScope.launch {
            if (isLoading.not()) {
                if (isUserLoged)
                    navController.navigate(MainScreen)
                if (!isUserLoged)
                    navController.navigate(LoginScreen)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(color = Purple40)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Fottow",
            fontSize = 50.sp,
            color = White,
            fontWeight = FontWeight.ExtraBold
        )
    }
}