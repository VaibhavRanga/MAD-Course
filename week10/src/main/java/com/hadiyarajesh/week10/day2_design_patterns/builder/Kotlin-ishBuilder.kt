package com.hadiyarajesh.week10.day2_design_patterns.builder

data class KotlinHome(
    private val doorHeight: DoorHeightInMeter,
    private val isRooftopOpen: Boolean,
    private val wallColor: WallColor,
    private val hasInterior: Boolean
)

class KotlinHomeBuilder {
    var doorHeight: DoorHeightInMeter = DoorHeightInMeter(0)
    var isRooftopOpen: Boolean = false
    var wallColor: WallColor = WallColor("White")
    var hasInterior: Boolean = false

    fun build(): KotlinHome = KotlinHome(doorHeight, isRooftopOpen, wallColor, hasInterior)
}

fun kotlinHome(block: KotlinHomeBuilder.() -> Unit): KotlinHome {
    return KotlinHomeBuilder().apply(block).build()
}

fun main() {
    val kotlinHome = kotlinHome {
        doorHeight = DoorHeightInMeter(2)
        isRooftopOpen = true
        hasInterior = true
    }

    println("kotlinHome: $kotlinHome")
}
