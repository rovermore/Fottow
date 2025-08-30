package com.fottow.fottow.data.user.network

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Failure
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.base.Success
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FCMNetworkDatasource {

    suspend fun getNewFCMToken(): Result<String, Error> {
        return suspendCancellableCoroutine { continuation ->
            FirebaseMessaging.getInstance().token
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        continuation.resume(Failure(Error.UncompletedOperation()))
                        return@addOnCompleteListener
                    }

                    val newToken = task.result
                    if (newToken != null && newToken.isNotEmpty()) {
                        continuation.resume(Success(newToken))
                    } else {
                        continuation.resume(Failure(Error.UncompletedOperation()))
                    }
                }
        }
    }

    suspend fun deleteFCMToken(): Result<Boolean, Error> {
        return suspendCancellableCoroutine { continuation ->
            FirebaseMessaging.getInstance().deleteToken()
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        continuation.resume(Failure(Error.UncompletedOperation()))
                        return@addOnCompleteListener
                    }

                    continuation.resume(Success(true))
                }
        }
    }
}