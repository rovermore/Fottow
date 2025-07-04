package com.fottow.fottow.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.fottow.fottow.presentation.gallery.GalleryScreen
import com.fottow.fottow.presentation.profile.ProfileScreen
import com.fottow.fottow.presentation.theme.White
import com.fottow.fottow.presentation.upload.UploadScreen
import com.fottow.fottow.presentation.widgets.ScreenContainer

@Composable
fun MainScreen(
    navController: NavController
) {
    var screenSelected by rememberSaveable(stateSaver = ScreenSelectedSaver) {
        mutableStateOf<ScreenSelected>(ScreenSelected.Gallery)
    }

    ScreenContainer(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.background(White)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        onClick = { screenSelected = ScreenSelected.Gallery },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.Home, "Gallery")
                    }
                    IconButton(
                        onClick = { screenSelected = ScreenSelected.Upload },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.AddCircle, "UploadScreen")
                    }
                    IconButton(
                        onClick = { screenSelected = ScreenSelected.Profile },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.Person, "ProfileScreen")
                    }
                }
            }
        }
    ) {
        when(screenSelected) {
            is ScreenSelected.Gallery -> GalleryScreen(navController = navController)
            is ScreenSelected.Upload -> UploadScreen(navController = navController)
            else -> ProfileScreen(navController = navController)
        }
    }
}

sealed class ScreenSelected {
    data object Gallery : ScreenSelected()
    data object Upload : ScreenSelected()
    data object Profile : ScreenSelected()
}

val ScreenSelectedSaver = Saver<ScreenSelected, String>(
    save = { screen ->
        when (screen) {
            is ScreenSelected.Gallery -> "Gallery"
            is ScreenSelected.Upload -> "Upload"
            is ScreenSelected.Profile -> "Profile"
        }
    },
    restore = { value ->
        when (value) {
            "Gallery" -> ScreenSelected.Gallery
            "Upload" -> ScreenSelected.Upload
            "Profile" -> ScreenSelected.Profile
            else -> ScreenSelected.Gallery
        }
    }
)