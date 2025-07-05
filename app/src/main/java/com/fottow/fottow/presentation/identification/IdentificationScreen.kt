package com.fottow.fottow.presentation.identification

import android.Manifest
import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.fottow.fottow.presentation.navigation.MainScreen
import com.fottow.fottow.presentation.theme.AppTheme
import com.fottow.fottow.presentation.theme.Typography
import com.fottow.fottow.presentation.widgets.ErrorView
import com.fottow.fottow.presentation.widgets.PrimaryButton
import com.fottow.fottow.presentation.widgets.ScreenContainer
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun IdentificationScreen(
    viewModel: IdentificationViewModel = koinViewModel<IdentificationViewModel>(),
    navController: NavController
) {

    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val uploadSuccessful by viewModel.uploadSuccessful.collectAsStateWithLifecycle()
    val isError by viewModel.onError.collectAsStateWithLifecycle()

    val context = LocalContext.current
    var cameraPhotoUri by remember { mutableStateOf(value = Uri.EMPTY) }

    val takePhoto = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { saved ->
        if (saved && cameraPhotoUri != Uri.EMPTY) viewModel.selectImage(cameraPhotoUri)
    }

    val cameraPermission = Manifest.permission.CAMERA
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { }

    val cameraPermissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA,
        onPermissionResult = { granted ->
            if (granted)
                takePhoto.launch(cameraPhotoUri)
            else
                cameraPermissionLauncher.launch(cameraPermission)
        }
    )

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
        if (uploadSuccessful) navController.navigate(MainScreen)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.Spacing.L)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(AppTheme.Spacing.L),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Mandanos un selfie para identificarte",
                style = MaterialTheme.typography.headlineMedium
            )

            PrimaryButton(
                text = "Take photo",
                onClick = {
                    val values = ContentValues()
                    values.put(MediaStore.Images.Media.TITLE, "Back Picture")
                    values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
                    cameraPhotoUri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                    cameraPermissionState.launchPermissionRequest()
                }
            )

            if (isError) ErrorView {
            }
        }
    }
}
