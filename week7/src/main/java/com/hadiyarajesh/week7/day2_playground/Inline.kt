package com.hadiyarajesh.week7.day2_playground

// No need to mark as inline function ,expected performance gain is insignificant
fun printInteger(int: Int) {
    println(int)
}

//inline fun doSomething(block: () -> Unit) {
//    block()
//}

inline fun doSomething(
    crossinline block: () -> Unit
) {
    block()
    anotherFunction(block)
}

inline fun doSomething(
    block1: () -> Unit,
    crossinline block2: () -> Unit,
    noinline logger: () -> Unit
) {
    block1()
    anotherFunction(block2)
//    val result = processLogger(logger)
    val result = logger
}

fun processLogger(function: () -> Unit) {

}

inline fun anotherFunction(block: () -> Unit) {
    block()
}

fun main() {
    printInteger(1)

//    doSomething {
//        println("Hello")
////        return // Non-local-return
//    }
}
