package com.hadiyarajesh.week7.day2_playground

fun printInt(data: Int) {
    println(data)
}

fun printString(data: String) {
    println(data)
}

// Generic function that print its data

fun <T> printType(data: T) {
    println(data)
}

//data class User()
//data class Swapna()
//data class Suraj()

// Co-variance
//class ProducerOutVariance<out T> {
//    // Returning T is allowed
//    fun test(): T {
//        TODO()
//    }
//
//    // Taking T is not allowed
//    fun testOne(t: T): T {
//        TODO()
//    }
//
//    fun testTwo(t: @UnsafeVariance T): T {
//        TODO()
//    }
//}

// Contra-variance
//class ProducerInVariance<in T> {
//    // Returning T is not allowed
//    fun test(): T {
//        TODO()
//    }
//
//    // Taking T is allowed
//    fun test(t: T) {
//        TODO()
//    }
//}

// No-variance
class ProducerNoVariance<T> {
    // Returning T is also allowed
    fun test(): T {
        TODO()
    }

    // Taking T is also allowed
    fun test(t: T) {
        TODO()
    }
}

fun main() {
//    printInt(12)
//    printString(12)

    printType<Int>(12)
    printType<String>("Rajesh")

    val intList = mutableListOf(1, 2, 3)
    val nameList = mutableListOf("Rajesh", "Suraj")
}
