package com.fottow.fottow.presentation.widgets

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.fottow.fottow.R
import com.fottow.fottow.presentation.isValidEmail

@Composable
fun EmailTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier
) {

    var isTouched by remember { mutableStateOf(false) }

    CustomTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = stringResource(R.string.email_label),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        isTouched = { isTouched = true },
        isValid = value.text.isValidEmail(),
        errorMessage = if (isTouched && value.text.length > 5) stringResource(R.string.invalid_email) else ""
    )

}