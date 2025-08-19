package com.fottow.fottow.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.fottow.fottow.R
import com.fottow.fottow.presentation.navigation.GalleryScreen
import com.fottow.fottow.presentation.navigation.LoginScreen
import com.fottow.fottow.presentation.navigation.MainScreen
import com.fottow.fottow.presentation.navigation.OnBoardingScreen
import com.fottow.fottow.presentation.theme.Primary
import com.fottow.fottow.presentation.theme.Purple40
import com.fottow.fottow.presentation.theme.White
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = koinViewModel<SplashScreenViewModel>(),
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = state) {
        coroutineScope.launch {
            if (state.loading.not()) {
                if (state.isUserLogged)
                    navController.navigate(MainScreen)
                if (!state.isUserLogged) {
                    if (state.isFirstInstall.not())
                        navController.navigate(LoginScreen)
                    else
                        navController.navigate(OnBoardingScreen)

                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.app_name),
            fontSize = 50.sp,
            color = White,
            fontWeight = FontWeight.ExtraBold
        )
    }
}