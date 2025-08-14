package com.fottow.fottow.presentation

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import com.fottow.fottow.FileResolver
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.photo.usecase.PhotoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

data class UploadResult(val success: Boolean, val errorMessage: String?)

object UploadResultManager {
    private val _uploadResult = MutableSharedFlow<UploadResult>()
    val uploadResult: SharedFlow<UploadResult> = _uploadResult.asSharedFlow()

    suspend fun emitResult(result: UploadResult) {
        _uploadResult.emit(result)
    }
}

class UploadPhotoService : Service() {

    private val updatePhotoUseCase: PhotoUseCase by inject()
    private val fileResolver: FileResolver by inject()

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val notificationManager by lazy { getSystemService(NotificationManager::class.java) }

    companion object {
        private const val UPLOAD_NOTIFICATION_ID = 1
        private const val RESULT_NOTIFICATION_ID = 2
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val imageUri = intent?.data

        startForeground(UPLOAD_NOTIFICATION_ID, createNotification("Subiendo fotos...", false))

        scope.launch {
            imageUri?.let {
                val fileName = "upload_${System.currentTimeMillis()}.jpg"
                val tempFile = fileResolver.copyUriToTempFile(imageUri, fileName)
                if (tempFile != null) {
                    updatePhotoUseCase.uploadPhoto(tempFile.absolutePath)
                        .map {
                            showResultNotification("Foto subida exitosamente", true)
                            UploadResultManager.emitResult(UploadResult(true, null))
                            stopForeground(true)
                            stopSelf()
                        }.mapFailure { error ->
                            val errorMessage =
                                "Error al subir la foto: ${error.message ?: "Error desconocido"}"
                            showResultNotification(errorMessage, false)
                            UploadResultManager.emitResult(UploadResult(false, errorMessage))
                            stopForeground(true)
                            stopSelf()
                        }
                } else {
                    val errorMessage = "Error: No se pudo acceder al archivo"
                    showResultNotification(errorMessage, false)
                    UploadResultManager.emitResult(UploadResult(false, errorMessage))
                    stopForeground(true)
                    stopSelf()
                }
            } ?: run {
                val errorMessage = "Error: No se proporcionó imagen válida"
                showResultNotification(errorMessage, false)
                UploadResultManager.emitResult(UploadResult(false, errorMessage))
                stopForeground(true)
                stopSelf()
            }
        }
        return START_NOT_STICKY
    }

    private fun showResultNotification(text: String, isSuccess: Boolean) {
        val notification = createNotification(text, true, isSuccess)
        notificationManager?.notify(RESULT_NOTIFICATION_ID, notification)
    }

    override fun onBind(p0: Intent?): IBinder? = null


    private fun createNotification(
        text: String,
        isPersistent: Boolean = false,
        isSuccess: Boolean = false
    ): Notification {
        val channelId = "upload_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Subida",
                if (isPersistent) NotificationManager.IMPORTANCE_DEFAULT else NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)
        }

        val icon = when {
            isSuccess -> android.R.drawable.stat_sys_upload_done
            isPersistent && !isSuccess -> android.R.drawable.stat_notify_error
            else -> android.R.drawable.stat_sys_upload
        }

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Subida de imágenes")
            .setContentText(text)
            .setSmallIcon(icon)
            .setOngoing(false) // Permite que el usuario pueda deslizar para eliminar
            .setAutoCancel(false) // No se cancela automáticamente al tocar
            .setPriority(if (isPersistent) NotificationCompat.PRIORITY_DEFAULT else NotificationCompat.PRIORITY_LOW)
            .build()
    }

}
