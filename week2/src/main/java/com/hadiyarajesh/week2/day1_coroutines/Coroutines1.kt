package com.hadiyarajesh.week2.day1_coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Starting the main")
//    println("Current thread in main is: ${Thread.currentThread().name}")
    // Create a new coroutine using launch {} function
//    launch {
//        doSomething()
//    }

//    val data: Deferred<String> = async { getData() }
//    println(data.await())

    launch {
        doAnotherThing("Rajesh")
        doAnotherThing("Rajesh")
    }

    launch {
        doAnotherThing("Hadiya")
    }

    println("Done with main")
}

suspend fun doAnotherThing(value: String) {
    println("Delay started for $value")
    delay(5_000)
    println("Delay done")
}

suspend fun doSomething() {
    // It'll take some time to complete
    println("Adding a delay")
    println("Current thread in doSomething is: ${Thread.currentThread().name}")
    delay(5_000)
    println("Do something")
}

suspend fun getData(): String {
    println("Adding a delay")
    delay(5_000)
    return "Rajesh Hadiya"
}
