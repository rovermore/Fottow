package com.fottow.fottow.presentation

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.fottow.fottow.FileResolver
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.photo.usecase.PhotoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class UploadPhotoService(
    private val fileResolver: FileResolver,
    private val updatePhotoUseCase: PhotoUseCase
) : Service() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())


    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val imageUri = intent?.data

        startForeground(1, createNotification("Subiendo fotos..."))

        scope.launch {
            imageUri?.let {
                val imagePath = fileResolver.getRealPathFromUri(it)
                if (imagePath != null) {
                    updatePhotoUseCase.uploadPhoto(imagePath)
                        .map {
                            stopForeground(true)
                            stopSelf()
                        }.mapFailure {
                            RetryUploadPhotoWorker.enqueue(applicationContext, imageUri)
                        }
                }
            }
        }
        return START_NOT_STICKY
    }

    private fun createNotification(text: String): Notification {
        val channelId = "upload_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Subida", NotificationManager.IMPORTANCE_LOW)
            getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Subida de imágenes")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.stat_sys_upload)
            .build()
    }

}