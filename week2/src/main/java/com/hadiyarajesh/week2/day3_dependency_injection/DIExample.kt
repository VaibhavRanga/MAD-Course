package com.hadiyarajesh.week2.day3_dependency_injection

data class Tyre(val company: String)
data class Chassis(val length: Int, val width: Int)
//data class Engine(val horsepower: Int)

interface Engine {
    val horsepower: Int
    val type: String
}

class PetrolEngine(override val horsepower: Int) : Engine {
    override val type: String
        get() = "Petrol"
}

class DieselEngine(override val horsepower: Int) : Engine {
    override val type: String
        get() = "Diesel"
}

class Car(
    val tyres: List<Tyre>, // Dependency
    val chassis: Chassis, // Dependency
    val engine: Engine // Dependency
) {
//    val tyre1 = Tyre("Pirelli")
//    val tyre2 = Tyre("Pirelli")
//    val tyre3 = Tyre("Pirelli")
//    val tyre4 = Tyre("Pirelli")
//
//    val chassis = Chassis(200, 100)
//    val engine = Engine(100)

    fun buildCar() {
        println("Car built with")
        println("Tyres company is: ${tyres.first().company}")
        println("Chassis length: ${chassis.length}, width: ${chassis.width}")
        println("Engine type: ${engine.type} horsepower: ${engine.horsepower}")
    }
}

fun main() {
    val tyres = listOf(Tyre("MRF"), Tyre("MRF"), Tyre("MRF"), Tyre("MRF"))
    val chassis = Chassis(300, 100)
    val petrolEngine = PetrolEngine(500)
    val dieselEngine = DieselEngine(500)

    val petrolCar = Car(tyres, chassis, petrolEngine) // Dependency injection - Manual
    val dieselCar = Car(tyres, chassis, dieselEngine) // Dependency injection - Manual
    petrolCar.buildCar()
    dieselCar.buildCar()
}
