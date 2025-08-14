package com.hadiyarajesh.week2.day3_dependency_injection

import android.util.Log

class PrivateClass private constructor() {
    lateinit var name: String

    fun printHello() {
        Log.d("TAG", "Hello from $name")
    }

    companion object {
        private var instance: PrivateClass? = null

        fun getInstance(name: String): PrivateClass {
            if (instance == null) {
                instance = PrivateClass().apply {
                    this.name = name
                }
            }

            return instance!!
        }
    }
}

//object PrivateClass