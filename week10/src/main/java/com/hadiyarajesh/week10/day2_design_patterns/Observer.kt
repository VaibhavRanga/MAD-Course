package com.hadiyarajesh.week10.day2_design_patterns

class RajeshFlow<T>(var value: T) {
    private val observers = mutableListOf<(T) -> Unit>()

    fun collect(observer: (T) -> Unit) {
        observers.add(observer)
        observer(value)
    }

    fun updateValue(newValue: T) {
        value = newValue
        notifyObservers()
    }

    private fun notifyObservers() {
        observers.forEach { observer ->
            observer(value)
        }
    }
}

fun <T> CreateOptimizedRajeshFlow(value: T): OptimizedRajeshFlow<T> {
    val instance = OptimizedRajeshFlow<T>()
    instance.value = value
    return instance
}

class OptimizedRajeshFlow<T> {
    var value: T? = null
        set(value) {
            field = value
            notifyObservers()
        }

    private val observers = mutableListOf<(T) -> Unit>()

    fun collect(observer: (T) -> Unit) {
        observers.add(observer)
        observer(value!!)
    }

//    fun updateValue(newValue: T) {
//        value = newValue
//        notifyObservers()
//    }

    private fun notifyObservers() {
        observers.forEach { observer ->
            observer(value!!)
        }
    }
}

fun main() {
//    val flow = RajeshFlow<String>("Initial value")
//
//    flow.collect {
//        println("Observer 1 received: $it")
//    }
//
//    flow.collect {
//        println("Observer 2 received: $it")
//    }
//
//    flow.value = ""
//    Thread.sleep(1_000)
//
//    flow.updateValue("New value")

//    Thread.sleep(1_000)

    val optimizedFlow = CreateOptimizedRajeshFlow("Initial value")

    optimizedFlow.collect {
        println("Observer 1 received: $it")
    }

    optimizedFlow.collect {
        println("Observer 2 received: $it")
    }

    Thread.sleep(1_000)

    optimizedFlow.value = "New value"

    Thread.sleep(1_000)
}
