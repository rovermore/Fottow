package com.fottow.fottow.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.fottow.fottow.BuildConfig
import com.fottow.fottow.presentation.navigation.LoginScreen
import com.fottow.fottow.presentation.theme.AppTheme
import com.fottow.fottow.presentation.widgets.ImageCustom
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    navController: NavController,
    setLogoutAction: ((() -> Unit) -> Unit)
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(setLogoutAction) {
        setLogoutAction { viewModel.logout() }
    }

    if (state.onLogout) {
        navController.navigate(LoginScreen)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.Spacing.L)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(AppTheme.Spacing.XL),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageCustom(
            modifier = Modifier
                .padding(AppTheme.Spacing.L)
                .size(300.dp)
                .clip(RoundedCornerShape(50)),
            imageUrl = state.userInfo.profileImage
        )
        Text(
            text = state.userInfo.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        Text(
            text = state.userInfo.email,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
        )

        Text(
            modifier = Modifier.padding(top = AppTheme.Spacing.L),
            text = "Version: ${BuildConfig.VERSION_NAME}",
            fontSize = 10.sp,
            fontWeight = FontWeight.W200,
        )
    }
}