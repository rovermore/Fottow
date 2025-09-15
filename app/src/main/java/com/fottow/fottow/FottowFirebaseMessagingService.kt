package com.fottow.fottow

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.fottow.fottow.domain.user.usecase.UserUseCase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FottowFirebaseMessagingService : FirebaseMessagingService() {

    private val userUseCase by inject<UserUseCase>()

    private val serviceScope = CoroutineScope(Dispatchers.IO)

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("FottowFCM", "Mensaje recibido de: ${remoteMessage.from}")
        remoteMessage.notification?.let {
            showNotification(it.title, it.body)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FottowFCM", "Nuevo token FCM: $token")
        serviceScope.launch {
            userUseCase.setFCMToken(token)
        }
    }

    private fun showNotification(title: String?, message: String?) {
        val channelId = "fcm_default_channel"
        val channel = NotificationChannel(
            channelId,
            "Mensajes FCM",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.w(
                    "FottowFCM",
                    "Permiso POST_NOTIFICATIONS no concedido. No se muestra la notificación."
                )
                return
            }
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification_logo)
            .setContentTitle(title ?: "Notificación")
            .setContentText(message ?: "Nuevo mensaje recibido")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        NotificationManagerCompat.from(this)
            .notify(System.currentTimeMillis().toInt(), notification)
    }
}
