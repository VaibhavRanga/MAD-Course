package com.hadiyarajesh.week1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log

class WifiEventReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            val wifiState =
                intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)

            when (wifiState) {
                WifiManager.WIFI_STATE_ENABLED -> {
                    Log.d("TAG", "Wifi is enabled")
                }

                WifiManager.WIFI_STATE_DISABLED -> {
                    Log.d("TAG", "Wifi is disabled")
                }

                else -> {
                    Log.d("TAG", "Other Wifi state")
                }
            }
        }
    }
}
