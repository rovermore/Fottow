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
import android.content.Intent
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {

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

        handleShareIntent(intent)

        setContent {
            FottowTheme {
                KoinAndroidContext {
                    FottowNavHost(isSharedPhoto = getSharedPhotoUri(intent) != null)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleShareIntent(intent)
    }

    private fun handleShareIntent(receivedIntent: Intent?) {
        val pendingShareUri = getSharedPhotoUri(receivedIntent)

        pendingShareUri?.let {
            val intent = Intent(this, UploadPhotoService::class.java).apply {
                data = pendingShareUri
            }
            ContextCompat.startForegroundService(this, intent)
        }
    }

    private fun getSharedPhotoUri(receivedIntent: Intent?): Uri? {
        return if (
            receivedIntent?.action == Intent.ACTION_SEND &&
            receivedIntent.type?.startsWith("image/") == true
        ) {
            receivedIntent.getParcelableExtra(Intent.EXTRA_STREAM)
        } else null
    }
}
