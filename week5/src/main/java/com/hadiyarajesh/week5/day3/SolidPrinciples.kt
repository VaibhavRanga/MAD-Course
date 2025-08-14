package com.hadiyarajesh.week5.day3

// SOLID principle stands for
// - S: Single Responsibility Principle
// - O: Open/Closed Principle
// - L: Liskov Substitution Principle
// - I: Interface Segregation Principle
// - D: Dependency Inversion Principle

// S: Single Responsibility Principle
class UserClass {
    fun getUsers() {}
//    fun getOrders() {}
//    fun getPayment() {}
}

class OrderClass {
    fun getOrders() {}
    fun getOrders(seed: String) {}
}

class PaymentClass {
    fun getPayment() {}
}

// O: Open/Closed Principle
open class RegularUser {
    open fun getProfile() {}
    // ❌ Modification
}

class PremiumUser : RegularUser() {
    override fun getProfile() {} // fancy things
}

class UltraRichUser : RegularUser() {
    override fun getProfile() {} // fancy things
}

// L: Liskov Substitution Principle
open class Animal {
    open fun makeSound() {
        println("Animal is making a sound")
    }
}

class Dog : Animal() {
    override fun makeSound() {
//        super.makeSound()
        println("Dog is barking")
    }
}

interface Fly {
    fun flying() {}
}

open class Bird {
    val color: String = "blue"
//    open fun fly() {}
}

open class FlyingBird : Bird(), Fly {}

class Pigeon : FlyingBird() {}

class Penguin : Bird() {
//    override fun fly() {
//        throw IllegalStateException("Penguin cannot fly")
//    }
}

fun doSomethingWithAnimal(
    animal: Dog
) {
    animal.makeSound()
}

// I: Interface Segregation Principle
interface BankAccount {
    fun deposit()
    fun withdraw()
}

interface ChequeBookAccount {
    fun printChequebook()
}

class RegularCustomerAccount : BankAccount {
    override fun deposit() {}
    override fun withdraw() {}

//    override fun printChequebook() {}
}

class PremiumCustomerAccount : BankAccount, ChequeBookAccount {
    override fun deposit() {}
    override fun withdraw() {}
    override fun printChequebook() {}
}

// D. Dependency Inversion Principle
interface Logger {
    fun log(message: String)
}

class ConsoleLogger : Logger {
    override fun log(message: String) {
        println(message)
    }
}

class FileLogger : Logger {
    override fun log(message: String) {
        // write to file
    }
}

class LoggerService(private val logger: Logger) {
    fun log(message: String) {
        logger.log(message)
    }
}
