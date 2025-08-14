package com.hadiyarajesh.week10.day2_design_patterns.factory

enum class State {
    Uttarakhand,
    Delhi,
    Orissa,
    Maharashtra
}

interface FamousPlace {
    val name: String
    val populationFactor: Int // On a scale of 1 to 10
}

fun main() {
    val factory = FamousPlaceFactory()
    val famousPlaceInUttarakhand = factory.get(State.Uttarakhand)
    println("Famous place name: ${famousPlaceInUttarakhand.name}")
    println("Famous place populationFactor: ${famousPlaceInUttarakhand.populationFactor}")
}
