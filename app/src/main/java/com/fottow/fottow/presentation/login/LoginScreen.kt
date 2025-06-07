package com.fottow.fottow.presentation.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.fottow.fottow.presentation.navigation.MainScreen
import com.fottow.fottow.presentation.navigation.RegisterScreen
import com.fottow.fottow.presentation.theme.AppTheme
import com.fottow.fottow.presentation.theme.Typography
import com.fottow.fottow.presentation.widgets.ErrorView
import com.fottow.fottow.presentation.widgets.PrimaryButton
import com.fottow.fottow.presentation.widgets.SecondaryButton
import com.fottow.fottow.presentation.widgets.ScreenContainer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel<LoginViewModel>(),
    navController: NavController
) {
    val login by viewModel.login.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    ScreenContainer(
        topBar = { TopAppBar(
            colors = AppTheme.TopAppColors.colors,
            title = {
                Text(
                    text = "Fottow",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp
                )
            })
        }

    ) {
        if (login) navController.navigate(MainScreen)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.Spacing.L),
            verticalArrangement = Arrangement.spacedBy(AppTheme.Spacing.L),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            PrimaryButton(
                text = "Log in",
                onClick = {
                    viewModel.logUser(email.text, password.text)
                }
            )

            SecondaryButton(
                text = "Regístrate",
                onClick = {
                    navController.navigate(RegisterScreen)
                }
            )

            if (error) ErrorView {  }
        }
    }

}
