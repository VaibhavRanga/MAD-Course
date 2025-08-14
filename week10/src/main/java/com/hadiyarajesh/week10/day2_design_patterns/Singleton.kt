package com.hadiyarajesh.week10.day2_design_patterns

// Restrict to create only 1 (single) instance
class SingletonDatabase private constructor() {
    companion object {
        private var instance: SingletonDatabase? = null

        fun getInstance(): SingletonDatabase {
            if (instance == null) {
                instance = SingletonDatabase()
            }

            return instance!!
        }
    }
}

object KotlinSingletonDatabase

fun main() {
//    SingletonDatabase.instance = SingletonDatabase.getInstance()
    val db1 = SingletonDatabase.getInstance()
    val db2 = SingletonDatabase.getInstance()

    println(db1)
    println(db2)
    println("Objects equals: ${db1 == db2}")

    val db3 = KotlinSingletonDatabase
    val db4 = KotlinSingletonDatabase

    println(db3)
    println(db4)
    println("Objects equals: ${db3 == db4}")
}
