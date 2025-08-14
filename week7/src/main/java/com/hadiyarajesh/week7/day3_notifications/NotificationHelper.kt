package com.hadiyarajesh.week7.day3_notifications

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.hadiyarajesh.week7.R

object NotificationHelper {
    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                context.getString(R.string.notification_channel_id),
                context.getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = context.getString(R.string.notification_channel_description)
            }

            val notificationManager =
                context.getSystemService<NotificationManager>(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createBasicNotification(context: Context) {
        val notificationId = 123
        val notification: Notification =
            NotificationCompat.Builder(context, context.getString(R.string.notification_channel_id))
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Basic Notification")
                .setContentText("This is a basic notification")
                .build()

        context.showNotification(notificationId, notification)
    }

    fun createClickableNotification(context: Context) {
        val notificationId = 456
        val notificationTitle = "Clickable Notification"
        val notificationText = "This is a clickable notification"

        val intent = Intent(context, NotificationDetailActivity::class.java).apply {
            putExtra("title", notificationTitle)
            putExtra("text", notificationText)
        }

        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification: Notification =
            NotificationCompat.Builder(context, context.getString(R.string.notification_channel_id))
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(notificationTitle)
                .setContentText(notificationText)
                .setContentIntent(pendingIntent)
                .build()

        context.showNotification(notificationId, notification)
    }


    fun createActionableNotification(context: Context) {
        val notificationId = 789
        val notificationTitle = "Actionable Notification"
        val notificationText = "This is a actionable notification"

        val intent = Intent(context, NotificationReceiver::class.java).apply {
            action = "MARK_AS_READ_ACTION"
            putExtra("title", notificationTitle)
            putExtra("text", notificationText)
        }

        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification: Notification =
            NotificationCompat.Builder(context, context.getString(R.string.notification_channel_id))
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(notificationTitle)
                .setContentText(notificationText)
                .setStyle(NotificationCompat.CallStyle())
//                .setAutoCancel(false)
                .setOngoing(true)
                .addAction(
                    R.drawable.ic_mark_as_read,
                    "Mark as read",
                    pendingIntent
                )
                .build()

        context.showNotification(notificationId, notification)
    }

    private fun Context.showNotification(
        notificationId: Int,
        notification: Notification
    ) {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        NotificationManagerCompat.from(this).notify(notificationId, notification)
    }
}
