package com.hadiyarajesh.week7.day2_playground

enum class Fruit {
    MANGO,
    APPLE
}

sealed class SealedFruit {
    data class Mango(
        val volumeOfPotassium: Int,
        val pulpAmount: Int
    ) : SealedFruit()

    data class Apple(
        val volumeOfCalcium: Float,
        val numberOfSlice: Double
    ) : SealedFruit()
}

fun main() {
    val sealedFruit: SealedFruit = SealedFruit.Mango(1, 1)

    when (sealedFruit) {
        is SealedFruit.Apple -> {
            println("Apple")
        }

        is SealedFruit.Mango -> {
            println("Mango")
        }
    }
}
