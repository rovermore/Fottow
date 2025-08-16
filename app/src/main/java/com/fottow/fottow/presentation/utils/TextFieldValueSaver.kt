package com.fottow.fottow.presentation.utils

import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.text.input.TextFieldValue

object TextFieldValueSaver {
    val Saver: Saver<TextFieldValue, String> = Saver(
        save = { it.text },
        restore = { TextFieldValue(it) }
    )
}