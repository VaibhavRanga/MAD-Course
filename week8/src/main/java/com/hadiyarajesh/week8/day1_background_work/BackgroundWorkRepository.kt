package com.hadiyarajesh.week8.day1_background_work

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

interface BackgroundWorkRepository {
    fun scheduleOneTimeWorkRequest()
    suspend fun scheduleOneTimeWorkRequestWithData()
    suspend fun scheduleOneTimeWorkRequestWithConstraints()
    suspend fun schedulePeriodicWorkRequest()
}

class BackgroundWorkRepositoryImpl @Inject constructor(
    private val workManager: WorkManager
) : BackgroundWorkRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun scheduleOneTimeWorkRequest() {
        val workRequest = OneTimeWorkRequest.Builder(LogWorker::class)
            .setInitialDelay(Duration.of(5, ChronoUnit.SECONDS))
            .build()

        workManager.enqueue(workRequest)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun scheduleOneTimeWorkRequestWithData() {
        val inputData = Data.Builder()
            .putString("name", "Rajesh")
            .putBoolean("isActive", Random.nextBoolean())
            .build()

        val workRequest = OneTimeWorkRequest.Builder(LogWorker::class)
            .setInitialDelay(Duration.of(5, ChronoUnit.SECONDS))
            .setInputData(inputData)
            .build()

        workManager.enqueue(workRequest)

        workManager.getWorkInfoByIdFlow(workRequest.id).collect { workInfo ->
            workInfo?.let {
                when (workInfo.state) {
                    WorkInfo.State.RUNNING -> {
                        Log.d("TAG", "Work in progress")
                    }

                    WorkInfo.State.SUCCEEDED -> {
                        val succeed = workInfo.outputData.getBoolean("succeed", false)
                        Log.d("TAG", "Work finished successfully: $succeed")
                    }

                    WorkInfo.State.FAILED -> {
                        val succeed = workInfo.outputData.getBoolean("succeed", false)
                        Log.d("TAG", "Work finished with errors: $succeed")
                    }

                    else -> {
                        Log.d("TAG", "Work state: ${workInfo.state}")
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun scheduleOneTimeWorkRequestWithConstraints() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = OneTimeWorkRequest.Builder(LogWorker::class)
            .setInitialDelay(Duration.of(5, ChronoUnit.SECONDS))
            .setConstraints(constraints)
            .build()

        workManager.enqueue(workRequest)

        workManager.getWorkInfoByIdFlow(workRequest.id).collect { workInfo ->
            workInfo?.let {
                Log.d("TAG", "Work state: ${workInfo.state}")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun schedulePeriodicWorkRequest() {
        val workRequest = PeriodicWorkRequest
            .Builder(LogWorker::class, 15, TimeUnit.MINUTES)
            .setInitialDelay(Duration.of(5, ChronoUnit.SECONDS))
            .build()

        workManager.enqueue(workRequest)

        workManager.getWorkInfoByIdFlow(workRequest.id).collect { workInfo ->
            workInfo?.let {
                Log.d("TAG", "Work state: ${workInfo.state}")
            }
        }
    }
}
