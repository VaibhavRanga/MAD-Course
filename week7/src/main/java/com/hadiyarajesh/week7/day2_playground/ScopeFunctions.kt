package com.hadiyarajesh.week7.day2_playground

import kotlin.random.Random

/**
 * Scope functions
 * 1. let {}
 * 2. apply {}
 * 3. also {}
 * 4. run {}
 * 5. with {}
 */

data class User(val id: String) {
    var name: String = ""
    var age: Int = 0

    override fun toString(): String {
        return "User(id='$id', name='$name', age='$age')"
    }
}

fun main() {
    // Java-ish
    val javaUser = User("123")
    javaUser.name = "Rajesh"
    javaUser.age = 25

    // Kotlin-ish
    val applyUser = User("123").apply {
        this.name = "Rajesh"
        this.age = 25
    }

//    val alsoUser = User("123").also {
//        it.name = "Rajesh"
//        it.age = 25
//
//        println("User is created")
//    }

//    println(applyUser)
//    println(alsoUser)

//    val user = getUser()
//    val result = user?.let {
//        println("User: $it")
//        5
//    } ?: run {
//        println("Sorry, User is null")
//        println("Very sorry User is null")
//    }
//
//    println("result: $result")

    val withUser = User(id = "123")

    val withResult = with(withUser) {
        name = "Rajesh"
        age = 29
        printGreetings()
    }

    println(withResult)

}

fun User.printGreetings() {
    println("Hello $name")
}

fun getUser(): User? {
    val user = User("123")

    return if (Random.nextBoolean()) {
        println("User is not null")
        user
    } else {
        println("User is null")
        null
    }
}
