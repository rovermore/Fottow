package com.fottow.fottow.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.fottow.fottow.R
import com.fottow.fottow.presentation.navigation.LoginScreen
import com.fottow.fottow.presentation.theme.AppTheme
import com.fottow.fottow.presentation.widgets.FTopBar
import com.fottow.fottow.presentation.widgets.ScreenContainer
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    navController: NavController
) {

    val onLogout by viewModel.onLogout.collectAsStateWithLifecycle()

    if (onLogout) {
        navController.navigate(LoginScreen)
    }

    ScreenContainer(
        topBar = {
            FTopBar(
                icon = R.drawable.ic_logout
            ) {
                viewModel.logout()
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(AppTheme.Spacing.L),
            verticalArrangement = Arrangement.spacedBy(AppTheme.Spacing.L),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }

    }
}