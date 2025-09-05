package com.fottow.fottow.presentation.upload

import android.Manifest
import android.os.Build
import androidx.compose.foundation.verticalScroll
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.fottow.fottow.R
import com.fottow.fottow.presentation.UploadPhotoService
import com.fottow.fottow.presentation.navigation.IdentificationScreen
import com.fottow.fottow.presentation.theme.AppTheme
import com.fottow.fottow.presentation.widgets.ErrorView
import com.fottow.fottow.presentation.widgets.Loader
import com.fottow.fottow.presentation.widgets.PrimaryButton
import com.fottow.fottow.presentation.widgets.SecondaryButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun UploadScreen(
    viewModel: UploadViewModel = koinViewModel<UploadViewModel>(),
    navController: NavController
) {

    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val updateSuccessful by viewModel.uploadSuccessful.collectAsStateWithLifecycle()
    val onError by viewModel.onError.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val goToIdentificationScreen by viewModel.goToIdentificationScreen.collectAsStateWithLifecycle()

    val context = LocalContext.current

    var cameraPhotoUri by remember { mutableStateOf(value = Uri.EMPTY) }

    var lastImageUri by remember { mutableStateOf<Uri?>(null) }
    fun startUploadService() {
        lastImageUri?.let {
            val intent = Intent(context, UploadPhotoService::class.java).apply {
                data = lastImageUri
            }
            ContextCompat.startForegroundService(context, intent)
        } ?: viewModel.selectImage(cameraPhotoUri)
    }

    if (goToIdentificationScreen) {
        navController.navigate(IdentificationScreen)
    }

    val pickPicture =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
            if (imageUri != Uri.EMPTY) {
                lastImageUri = imageUri
                startUploadService()
            }
        }

    val takePhoto =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { saved ->
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

    val readMediaImagesPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val readMediaImagesPermissionState = rememberPermissionState(
        permission = readMediaImagesPermission,
        onPermissionResult = { granted ->
            if (granted) {
                pickPicture.launch("image/*")
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.Spacing.L)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(AppTheme.Spacing.L),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.upload_screen_title),
                fontSize = 24.sp,
                lineHeight = 32.sp,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        PrimaryButton(
            text = stringResource(id = R.string.take_photo),
            onClick = {
                val values = ContentValues()
                values.put(MediaStore.Images.Media.TITLE, context.getString(R.string.back_picture))
                values.put(MediaStore.Images.Media.DESCRIPTION, context.getString(R.string.from_camera))
                cameraPhotoUri = context.contentResolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    values
                )
                cameraPermissionState.launchPermissionRequest()
            }
        )

        SecondaryButton(
            text = stringResource(id = R.string.select_from_device),
            onClick = {
                readMediaImagesPermissionState.launchPermissionRequest()
            }
        )

        if (onError) {
            Dialog(
                onDismissRequest = { viewModel.dialogDismissed() }
            ) {
                ErrorView { startUploadService() }
            }
        }

    }

    if (updateSuccessful) {
        FDialog(stringResource(id = R.string.photo_uploaded_successfully)) { viewModel.dialogDismissed() }
    }

    if (isLoading) {
        Loader()
    }
}

@Composable
fun FDialog(
    message: String,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = message,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}
