package com.fottow.fottow.firebase

import com.google.firebase.messaging.FirebaseMessagingService

class FottowFirebaseMessagingService(

) : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}