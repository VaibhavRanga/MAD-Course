package com.hadiyarajesh.week5.day1_datastore

//sealed interface Currency {
//    data class Rupee(val count: Int) : Currency
//    data class USD(val count: Int) : Currency
//}

sealed interface Currency {
    @JvmInline
    value class Rupee(val count: Int) : Currency

    @JvmInline
    value class USD(val count: Int) : Currency
}

fun printMoney(currency: Currency) {
    when (currency) {
        is Currency.Rupee -> println("Printed ${currency.count} Rupees.")
        is Currency.USD -> println("Printed ${currency.count} USD.")
    }
}

fun printMoney(currency: Int) {}

fun main() {
    printMoney(Currency.Rupee(500))
    printMoney(Currency.USD(500))
}
