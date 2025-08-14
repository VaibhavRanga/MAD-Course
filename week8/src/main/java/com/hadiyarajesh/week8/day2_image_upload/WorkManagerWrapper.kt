package com.hadiyarajesh.week8.day2_image_upload

import androidx.work.Operation
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

interface WorkManagerWrapper {
    fun enqueue(request: WorkRequest): Operation
    fun getWorkInfoByIdFlow(id: UUID): Flow<WorkInfo?>
}

class WorkManagerWrapperImpl @Inject constructor(
    private val workManager: WorkManager
) : WorkManagerWrapper {
    override fun enqueue(request: WorkRequest): Operation {
        return workManager.enqueue(request)
    }

    override fun getWorkInfoByIdFlow(id: UUID): Flow<WorkInfo?> {
        return workManager.getWorkInfoByIdFlow(id)
    }
}
