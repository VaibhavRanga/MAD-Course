package com.hadiyarajesh.week9.day2_firebase.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMMessagingService : FirebaseMessagingService() {
    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "${FCMMessagingService::class.simpleName} created")
    }

    override fun onNewToken(token: String) {
        // Sample token: dMeUh6QFSKSeo9TyW4kc76:APA91bHhXLy8ryIO-OxEsj3t9lXLyTWrMDsMstTYputwVh-oFSRWxdmJ9K3PagmmhgEo5AJblBO6fakt_1lhWGtlq-JVVSqSlnecqcs0yXTooTjLAidX9eo
        super.onNewToken(token)
        Log.d("TAG", "Refreshed firebase token: $token")
        // Send token to server
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("TAG", "Message received: ${message.data}")

        val title = message.notification?.title
        val body = message.notification?.body

        Log.d("TAG", "Message notification title: $title and body: $body")

        NotificationHelper.createBasicNotification(
            context = this,
            title = title ?: "Default title",
            body = body ?: "Default body"
        )
    }
}
