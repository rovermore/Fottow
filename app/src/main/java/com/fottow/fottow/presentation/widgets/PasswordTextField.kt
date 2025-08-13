package com.fottow.fottow.presentation.widgets

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.fottow.fottow.R
import com.fottow.fottow.presentation.isValidPassword

@Composable
fun PasswordTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    showError: Boolean = true
) {

    var isTouched by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    CustomTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = "Contraseña",
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        isTouched = { isTouched = true },
        isValid = value.text.isValidPassword(),
        errorMessage = if (isTouched && value.text.length > 5 && showError) "La contraseña debe tener al menos 8 caracteres alfanumericos y un caracter especial" else "",
        icon = R.drawable.ic_eye,
        onIconClicked = { isPasswordVisible = !isPasswordVisible }
    )
}