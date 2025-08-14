package com.hadiyarajesh.week2.day3_dependency_injection

import javax.inject.Inject

interface DiRepository {
    fun getData(): String
}

class DiRepositoryImpl @Inject constructor(
//    @Named("RajeshClass")
    @VikasClass private val privateClass: PrivateClass
) : DiRepository {
    override fun getData(): String {
        privateClass.printHello()
        return "Rajesh Hadiya"
    }
}

class TestDiRepositoryImpl() : DiRepository {
    override fun getData(): String {
        return "Suraj Singh"
    }
}
