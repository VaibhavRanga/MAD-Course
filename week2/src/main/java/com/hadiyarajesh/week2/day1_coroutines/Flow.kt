package com.hadiyarajesh.week2.day1_coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun main() {
//    val items = getListItems()

//    items.forEach {
//        println("Receiving item: $it")
//        delay(1000)
//    }

    getFlowItem()
        .collect {
            println("Receiving item: $it")
            delay(500)
        }

    getFlowItem()
        .collect {
            println("Receiving item: $it")
            delay(500)
        }
}

suspend fun getListItems(): List<Int> {
    val list = (1..10).toList()

    list.forEach {
        println("Processing item: $it")
        delay(1000)
    }

    return list
}

// Cold flow
fun getFlowItem(): Flow<Int> = flow<Int> {
    (1..10)
        .onEach {
            println("Processing item: $it")
            delay(500)
            emit(it)
        }
}
