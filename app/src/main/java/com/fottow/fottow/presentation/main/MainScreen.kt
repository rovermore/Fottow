package com.fottow.fottow.presentation.main

import android.Manifest
import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fottow.fottow.presentation.theme.AppTheme
import com.fottow.fottow.presentation.theme.FottowTheme
import com.fottow.fottow.presentation.theme.Typography
import com.fottow.fottow.presentation.widgets.FTopBar
import com.fottow.fottow.presentation.widgets.Loader
import com.fottow.fottow.presentation.widgets.ScreenContainer
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel<MainViewModel>()
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val updateSuccessful by viewModel.uploadSuccessful.collectAsStateWithLifecycle()

    val context = LocalContext.current

    var cameraPhotoUri by remember { mutableStateOf(value = Uri.EMPTY) }

    val pickPicture = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
        viewModel.selectImage(imageUri)
    }

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
        topBar = { FTopBar() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(AppTheme.Spacing.L),
            verticalArrangement = Arrangement.spacedBy(AppTheme.Spacing.L),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                onClick = {
                    val values = ContentValues()
                    values.put(MediaStore.Images.Media.TITLE, "Back Picture")
                    values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
                    cameraPhotoUri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                    cameraPermissionState.launchPermissionRequest()
                },
            ) {
                Text(
                    text = "Take photo",
                    style = Typography.titleLarge
                )
            }
            OutlinedButton(
                onClick = {
                    pickPicture.launch("image/*")
                },
            ) {
                Text(
                    text = "Go to Gallery",
                    style = Typography.titleLarge
                )
            }
        }

        if (updateSuccessful) {
            FDialog { viewModel.dialogDismissed() }
        }

        if (isLoading) {
            Loader()
        }

    }
}

@Composable
fun FDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "Photo has been uploaded successfully",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    FottowTheme {
        MainScreen()
    }
}