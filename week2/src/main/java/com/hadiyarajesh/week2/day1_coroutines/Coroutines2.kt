package com.hadiyarajesh.week2.day1_coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

suspend fun main() {
    val handler = CoroutineExceptionHandler { context, exception ->
        println("Caught $exception")
    }

    val scope = CoroutineScope(Dispatchers.Default + SupervisorJob() + handler)

    println("Main started")
//    val parentJob = scope.launch {
//        launch { child1() }
//        launch { child2() }
//    }

//    delay(6.seconds)
//    parentJob.cancel()
//    parentJob.join()

    val job1 = scope.launch {
        child1()

//        val job1 = scope.launch { child1() }
//        val job2 = scope.launch { child2() }
//
//        joinAll(job1, job2)
    }

    val job2 = scope.launch { child2() }
    joinAll(job1, job2)

//    val job1 = scope.async { doOtherThing() }
//    val job2 = scope.async { doOtherThing() }

//    val data1 = job1.await()
//    val data2 = job2.await()
//    val datas = awaitAll(job1, job2)

    println("Main completed")
}

suspend fun doOtherThing() {
    println("Do doOtherThing started")
    delay(5.seconds)
    println("Do doOtherThing completed")
}

suspend fun child1() {
    println("Child1 started")
    delay(5.seconds)
    throw RuntimeException("Child 1 Exception")
    println("Child1 completed")
}

suspend fun child2() {
    println("Child2 started")
    throw RuntimeException("Child 2 Exception")
    delay(5.seconds)
    println("Child2 completed")
}
