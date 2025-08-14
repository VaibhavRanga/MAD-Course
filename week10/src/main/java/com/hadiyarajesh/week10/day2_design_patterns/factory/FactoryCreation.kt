package com.hadiyarajesh.week10.day2_design_patterns.factory

private data class Kedarnath(
    override val name: String = "Kedarnath",
    override val populationFactor: Int = 8
) : FamousPlace

private data class RedFort(
    override val name: String = "RedFort",
    override val populationFactor: Int = 8
) : FamousPlace

private data class JagannathPuri(
    override val name: String = "JagannathPuri",
    override val populationFactor: Int = 8
) : FamousPlace

private data class MarineDrive(
    override val name: String = "MarineDrive",
    override val populationFactor: Int = 8
) : FamousPlace

class FamousPlaceFactory() {
    fun get(state: State): FamousPlace {
        return when (state) {
            State.Uttarakhand -> Kedarnath()
            State.Delhi -> RedFort()
            State.Orissa -> JagannathPuri()
            State.Maharashtra -> MarineDrive()
        }
    }
}
