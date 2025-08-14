package com.hadiyarajesh.week1

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    val handler = Handler()
    val runnable = Runnable {
        Log.d("TAG", "This is printed from ${this.javaClass.simpleName}")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "${this.javaClass.simpleName} is created")
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.postDelayed(runnable, 10000)
        //        stopSelf()
        Log.d("TAG", "Intent is nullable: ${intent == null}")
        Log.d("TAG", "Returning from ${this.javaClass.simpleName}")
        Log.d("TAG", "Thread: ${Thread.currentThread().name}")
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "${this.javaClass.simpleName} is destroyed")
    }
}
