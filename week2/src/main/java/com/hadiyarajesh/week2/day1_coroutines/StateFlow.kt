package com.hadiyarajesh.week2.day1_coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class HotFlowExample {
    val scope = CoroutineScope(Dispatchers.Default)

    private val _sharedFlow: MutableSharedFlow<Int> = MutableSharedFlow()
    val sharedFlow: SharedFlow<Int> = _sharedFlow.asSharedFlow()

    private val _stateFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    val stateFlow: StateFlow<Int> = _stateFlow.asStateFlow()

    init {
        scope.launch {
//            (1..10).forEach {
//                delay(1000)
////                _sharedFlow.emit(it)
//                _stateFlow.emit(it)
//            }

            listOf(5, 5, 5).onEach {
                delay(1000)
//                _sharedFlow.emit(it)
                _stateFlow.emit(it)
            }
        }
    }
}

suspend fun main() {
    val scope = CoroutineScope(Dispatchers.Default)
    val hotFlowExample = HotFlowExample()

    val job1 = scope.launch {
//        hotFlowExample.sharedFlow.collect {
//            println("Receiver 1 received value: $it")
//        }

        hotFlowExample.stateFlow.collect {
            println("Receiver 1 received value: $it")
        }
    }

    println("StateFlow value is: ${hotFlowExample.stateFlow.value}")

//    delay(3.seconds)
//
//    val job2 = scope.launch {
//        hotFlowExample.sharedFlow.collect {
//            println("Receiver 2 received value: $it")
//        }
//    }

    joinAll(job1)
//    joinAll(job1, job2)
}
