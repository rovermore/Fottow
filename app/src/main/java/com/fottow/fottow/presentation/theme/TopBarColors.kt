package com.fottow.fottow.presentation.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable

class TopBarColors {
    @OptIn(ExperimentalMaterial3Api::class)
    val colors @Composable get() =  TopAppBarColors(
    containerColor = MaterialTheme.colorScheme.primaryContainer,
    actionIconContentColor = MaterialTheme.colorScheme.onTertiary,
    navigationIconContentColor = MaterialTheme.colorScheme.onTertiary,
    scrolledContainerColor = MaterialTheme.colorScheme.onTertiary,
    titleContentColor = MaterialTheme.colorScheme.onTertiary
    )
}