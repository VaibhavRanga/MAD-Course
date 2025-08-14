package com.hadiyarajesh.week8.day3_testing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.Data
import androidx.work.Operation
import androidx.work.WorkInfo
import androidx.work.WorkRequest
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import com.hadiyarajesh.week8.day2_image_upload.WorkManagerWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class TestWorkManagerWrapper : WorkManagerWrapper {
    var throwError: Boolean = false
    var imageUrl: String = "https://example.com/image.jpg"

    override fun enqueue(request: WorkRequest): Operation {
        return TestOperation()
    }

    override fun getWorkInfoByIdFlow(id: UUID): Flow<WorkInfo?> {
        return flow {
            emit(
                WorkInfo(
                    id = id,
                    state = WorkInfo.State.ENQUEUED,
                    tags = emptySet(),
                )
            )

            emit(
                WorkInfo(
                    id = id,
                    state = WorkInfo.State.RUNNING,
                    tags = emptySet(),
                )
            )

            if (throwError) {
                emit(
                    WorkInfo(
                        id = id,
                        state = WorkInfo.State.FAILED,
                        tags = emptySet()
                    )
                )
            } else {
                emit(
                    WorkInfo(
                        id = id,
                        state = WorkInfo.State.SUCCEEDED,
                        tags = emptySet(),
                        outputData = Data.Builder()
                            .putString("imageUrl", imageUrl).build()
                    )
                )
            }
        }
    }
}

class TestOperation : Operation {
    private val liveData = MutableLiveData<Operation.State>()

    override fun getState(): LiveData<Operation.State?> {
        liveData.value = Operation.SUCCESS
        return liveData
    }

    override fun getResult(): ListenableFuture<Operation.State.SUCCESS?> {
        return Futures.immediateFuture(Operation.SUCCESS)
    }
}
