package com.hadiyarajesh.week10_library

import android.util.Log

object LoggingUtility {
    fun debugLog(msg: String, tag: String = "TAG") {
        Log.d(tag, msg)
    }
}
