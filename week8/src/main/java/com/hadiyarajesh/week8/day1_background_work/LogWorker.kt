package com.hadiyarajesh.week8.day1_background_work

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class LogWorker(
    val context: Context,
    val workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("TAG", "This is printed from ${LogWorker::class.java.simpleName} class.")

        val name = inputData.getString("name")
        val isActive = inputData.getBoolean("isActive", false)

        Log.d("TAG", "Name: $name, isActive: $isActive")

        val outputData = Data.Builder()
            .putBoolean("succeed", isActive)
            .build()

        return if (isActive) {
            Result.success(outputData)
        } else {
            Result.failure(outputData)
        }
    }
}
