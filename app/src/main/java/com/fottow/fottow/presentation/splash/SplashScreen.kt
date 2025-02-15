package com.fottow.fottow.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fottow.fottow.presentation.navigation.LoginScreen
import com.fottow.fottow.presentation.theme.Purple40
import com.fottow.fottow.presentation.theme.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = "") {
        coroutineScope.launch {
            delay(2000)
            navController.navigate(LoginScreen)
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