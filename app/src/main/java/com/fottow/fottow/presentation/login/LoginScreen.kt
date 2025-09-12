package com.fottow.fottow.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.fottow.fottow.R
import com.fottow.fottow.presentation.error.ErrorUi
import com.fottow.fottow.presentation.isValidEmail
import com.fottow.fottow.presentation.navigation.IdentificationScreen
import com.fottow.fottow.presentation.navigation.MainScreen
import com.fottow.fottow.presentation.navigation.RegisterScreen
import com.fottow.fottow.presentation.theme.AppTheme
import com.fottow.fottow.presentation.utils.TextFieldValueSaver
import com.fottow.fottow.presentation.widgets.EmailTextField
import com.fottow.fottow.presentation.widgets.ErrorView
import com.fottow.fottow.presentation.widgets.FTopBar
import com.fottow.fottow.presentation.widgets.Loader
import com.fottow.fottow.presentation.widgets.PasswordTextField
import com.fottow.fottow.presentation.widgets.PrimaryButton
import com.fottow.fottow.presentation.widgets.ScreenContainer
import com.fottow.fottow.presentation.widgets.SecondaryButton
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel<LoginViewModel>(),
    navController: NavController
) {
    val user by viewModel.user.collectAsStateWithLifecycle()
    val error by viewModel.onError.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    var email by rememberSaveable(stateSaver = TextFieldValueSaver.Saver) { mutableStateOf(TextFieldValue("")) }
    var password by rememberSaveable(stateSaver = TextFieldValueSaver.Saver) { mutableStateOf(TextFieldValue("")) }

    ScreenContainer(
        topBar = { FTopBar() },
        isLoading = isLoading,
    ) {
        if (user.email.isNotEmpty() && user.profileImage.isNotEmpty())
            navController.navigate(MainScreen)
        if (user.email.isNotEmpty() && user.profileImage.isEmpty())
            navController.navigate(IdentificationScreen)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.Spacing.L)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(AppTheme.Spacing.L),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_color),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(100.dp),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = stringResource(R.string.login_title),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineMedium
            )

            EmailTextField(
                value = email,
                onValueChange = { email = it },
            )

            PasswordTextField(
                value = password,
                onValueChange = { password = it },
                showError = false
            )

            PrimaryButton(
                text = stringResource(R.string.log_in),
                enabled = email.text.isValidEmail(),
                onClick = {
                    viewModel.logUser(email.text, password.text)
                }
            )

            SecondaryButton(
                text = stringResource(R.string.register_action),
                onClick = {
                    navController.navigate(RegisterScreen)
                }
            )

            if (error !is ErrorUi.None) ErrorView(
                message = error.message,
            )
        }
    }

}
