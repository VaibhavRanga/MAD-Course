package com.hadiyarajesh.week7.day3_notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "MARK_AS_READ_ACTION") {
            val title = intent.getStringExtra("title")
            val text = intent.getStringExtra("text")

            Log.d("TAG", "NotificationReceiver: Title: $title")
            Log.d("TAG", "NotificationReceiver: Text: $text")
        }
    }
}
