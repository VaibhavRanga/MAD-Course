package com.hadiyarajesh.week1

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var counterButton: Button
    lateinit var launchActivityB: Button
    lateinit var openGoogleWebsite: Button
    lateinit var startService: Button
    lateinit var stopService: Button
    lateinit var wifiReceiver: WifiEventReceiver
    var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        textView = findViewById(R.id.counterText)
        counterButton = findViewById(R.id.counterButton)
        launchActivityB = findViewById(R.id.launchActivityB)
        openGoogleWebsite = findViewById(R.id.openGoogleWebsite)
        startService = findViewById(R.id.startService)
        stopService = findViewById(R.id.stopService)

        counterButton.setOnClickListener {
            counter++
            textView.text = "Click count: ${counter}"
        }

        launchActivityB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            intent.putExtra("counter", counter)
            startActivity(intent)
        }

        openGoogleWebsite.setOnClickListener {
            val url = "https://www.google.com"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        wifiReceiver = WifiEventReceiver()
        registerReceiver(wifiReceiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))

        val serviceIntent = Intent(this, MyService::class.java)

        startService.setOnClickListener {
            startService(serviceIntent)
        }

        stopService.setOnClickListener {
            stopService(serviceIntent)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        unregisterReceiver(wifiReceiver)
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("counter", counter)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val counter = savedInstanceState.getInt("counter")
        textView.text = "Click count: $counter"
    }
}
