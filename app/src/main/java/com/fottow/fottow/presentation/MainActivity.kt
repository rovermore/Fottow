package com.fottow.fottow.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fottow.fottow.presentation.navigation.FottowNavHost
import com.fottow.fottow.presentation.theme.FottowTheme
import org.koin.androidx.compose.KoinAndroidContext
import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {

    private var pendingShareUri by mutableStateOf<Uri?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            }
        }

        setContent {
            FottowTheme {
                KoinAndroidContext {
                    FottowNavHost()
                }
            }
        }

        val receivedIntent = intent
        pendingShareUri = if (
            receivedIntent?.action == Intent.ACTION_SEND &&
            receivedIntent.type?.startsWith("image/") == true
        ) {
            receivedIntent.getParcelableExtra(Intent.EXTRA_STREAM)
        } else null

        pendingShareUri?.let {
            val intent = Intent(this, UploadPhotoService::class.java).apply {
                data = pendingShareUri
            }
            ContextCompat.startForegroundService(this, intent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val shareUri = extractSharedImageUri(intent)
        if (shareUri != null) {
            pendingShareUri = shareUri
        }
    }

    private fun extractSharedImageUri(sourceIntent: Intent?): Uri? {
        return if (
            sourceIntent?.action == Intent.ACTION_SEND &&
            sourceIntent.type?.startsWith("image/") == true
        ) {
            sourceIntent.getParcelableExtra(Intent.EXTRA_STREAM)
        } else null
    }

}

