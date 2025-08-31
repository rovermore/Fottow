package com.fottow.fottow.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.fottow.fottow.R
import com.fottow.fottow.presentation.isValidEmail
import com.fottow.fottow.presentation.isValidPassword
import com.fottow.fottow.presentation.navigation.IdentificationScreen
import com.fottow.fottow.presentation.navigation.TermsAndConditionsScreen
import com.fottow.fottow.presentation.theme.AppTheme
import com.fottow.fottow.presentation.utils.TextFieldValueSaver
import com.fottow.fottow.presentation.widgets.CustomTextField
import com.fottow.fottow.presentation.widgets.EmailTextField
import com.fottow.fottow.presentation.widgets.ErrorView
import com.fottow.fottow.presentation.widgets.FTopBar
import com.fottow.fottow.presentation.widgets.FottowCheckBox
import com.fottow.fottow.presentation.widgets.PasswordTextField
import com.fottow.fottow.presentation.widgets.PrimaryButton
import com.fottow.fottow.presentation.widgets.ScreenContainer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = koinViewModel<RegisterViewModel>(),
    navController: NavController
) {

    val register by viewModel.register.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    var email by rememberSaveable(stateSaver = TextFieldValueSaver.Saver) { mutableStateOf(TextFieldValue("")) }
    var password by rememberSaveable(stateSaver = TextFieldValueSaver.Saver) { mutableStateOf(TextFieldValue("")) }
    var nickName by rememberSaveable(stateSaver = TextFieldValueSaver.Saver) { mutableStateOf(TextFieldValue("")) }

    var isTermsAndConditionsChecked by rememberSaveable { mutableStateOf(false) }

    ScreenContainer(
        topBar = { FTopBar() }

    ) {
        if (register) navController.navigate(IdentificationScreen)

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
                text = stringResource(R.string.register_title),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineMedium
            )

            CustomTextField(
                value = nickName,
                onValueChange = { nickName = it },
                label = stringResource(R.string.register_username),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(),
                isValid = email.text.isValidEmail()

            )

            EmailTextField(
                value = email,
                onValueChange = { email = it },
            )

            PasswordTextField(
                value = password,
                onValueChange = { password = it },
            )

            FottowCheckBox(
                text = stringResource(R.string.register_terms),
                checked = isTermsAndConditionsChecked,
                onCheckedChange = { isTermsAndConditionsChecked = it }
            ) {
                navController.navigate(TermsAndConditionsScreen)
            }

            PrimaryButton(
                text = stringResource(R.string.sign_up),
                enabled = email.text.isValidEmail() && password.text.isValidPassword()
                        && isTermsAndConditionsChecked && nickName.text.isNotEmpty(),
                onClick = {
                    viewModel.registerUser(email.text, password.text, nickName.text)
                }
            )

            if (error.isNotEmpty())
                ErrorView(
                    message = error
                ) {
                    viewModel.registerUser(email.text, password.text, nickName.text)
                }
        }
    }
}
