package com.hadiyarajesh.week10.day2_design_patterns.builder

@JvmInline
value class DoorHeightInMeter(val value: Int)

@JvmInline
value class WallColor(val value: String)

data class Home private constructor(
    private val doorHeight: DoorHeightInMeter,
    private val isRooftopOpen: Boolean,
    private val wallColor: WallColor,
    private val hasInterior: Boolean
) {
    class Builder {
        private var doorHeight: DoorHeightInMeter = DoorHeightInMeter(0)
        private var isRooftopOpen: Boolean = false
        private var wallColor: WallColor = WallColor("White")
        private var hasInterior: Boolean = false

        fun doorHeight(doorHeight: DoorHeightInMeter): Builder {
            this.doorHeight = doorHeight
            return this
        }

        fun isRooftopOpen(isRooftopOpen: Boolean): Builder {
            this.isRooftopOpen = isRooftopOpen
            return this
        }

        fun wallColor(wallColor: WallColor): Builder {
            this.wallColor = wallColor
            return this
        }

        fun hasInterior(hasInterior: Boolean): Builder {
            this.hasInterior = hasInterior
            return this
        }

        fun build(): Home = Home(doorHeight, isRooftopOpen, wallColor, hasInterior)
    }
}

fun main() {
//    val home = Home(
//        doorHeight = DoorHeightInMeter(2),
//        isRooftopOpen = true,
//        wallColor = WallColor("Red"),
//        hasInterior = false
//    )

    val home = Home.Builder()
        .doorHeight(DoorHeightInMeter(2))
        .isRooftopOpen(true)
        .hasInterior(true)
        .build()

    println(home)
}
