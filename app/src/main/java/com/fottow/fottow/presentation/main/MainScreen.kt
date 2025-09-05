package com.fottow.fottow.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fottow.fottow.R
import com.fottow.fottow.presentation.gallery.GalleryScreen
import com.fottow.fottow.presentation.profile.ProfileScreen
import com.fottow.fottow.presentation.theme.AppTheme
import com.fottow.fottow.presentation.upload.UploadScreen
import com.fottow.fottow.presentation.widgets.ScreenContainer
import com.fottow.fottow.presentation.widgets.FTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    isSharedPhoto: Boolean = false
) {
    var screenSelected by rememberSaveable(stateSaver = ScreenSelectedSaver) {
        mutableStateOf<ScreenSelected>(ScreenSelected.Gallery)
    }

    if (isSharedPhoto) {
        screenSelected = ScreenSelected.Upload
    }

    var onProfileLogout by androidx.compose.runtime.remember { mutableStateOf<() -> Unit>({}) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    ScreenContainer(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            when (screenSelected) {
                is ScreenSelected.Gallery -> FTopBar(scrollBehavior = scrollBehavior)
                is ScreenSelected.Upload -> FTopBar { }
                is ScreenSelected.Profile -> FTopBar(
                    icon = R.drawable.ic_logout
                ) {
                    onProfileLogout()
                }
            }
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = AppTheme.Spacing.XS),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = { screenSelected = ScreenSelected.Gallery },
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_photo),
                                contentDescription = "Gallery",
                                modifier = Modifier.size(24.dp),
                                tint = if (screenSelected is ScreenSelected.Gallery) MaterialTheme.colorScheme.primary else Color.Gray
                            )

                            Text(
                                text = stringResource(id = R.string.tab_gallery),
                                style = MaterialTheme.typography.labelSmall,
                                color = if (screenSelected is ScreenSelected.Gallery) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                        }

                    }

                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = { screenSelected = ScreenSelected.Upload },
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                Icons.Default.AddCircle,
                                contentDescription = "Upload",
                                modifier = Modifier.size(24.dp),
                                tint = if (screenSelected is ScreenSelected.Upload) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                            Text(
                                text = stringResource(id = R.string.tab_upload),
                                style = MaterialTheme.typography.labelSmall,
                                color = if (screenSelected is ScreenSelected.Upload) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                        }

                    }

                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = { screenSelected = ScreenSelected.Profile },
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Profile",
                                modifier = Modifier.size(24.dp),
                                tint = if (screenSelected is ScreenSelected.Profile) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                            Text(
                                text = stringResource(id = R.string.tab_profile),
                                style = MaterialTheme.typography.labelSmall,
                                color = if (screenSelected is ScreenSelected.Profile) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                        }

                    }
                }
            }
        }
    ) {
        when (screenSelected) {
            is ScreenSelected.Gallery -> GalleryScreen(navController = navController)
            is ScreenSelected.Upload -> UploadScreen(navController = navController)
            is ScreenSelected.Profile -> ProfileScreen(
                navController = navController,
                setLogoutAction = { onProfileLogout = it }
            )
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