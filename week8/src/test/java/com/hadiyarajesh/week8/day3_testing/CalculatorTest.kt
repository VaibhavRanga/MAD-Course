package com.hadiyarajesh.week8.day3_testing

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CalculatorTest {
    private lateinit var calculator: Calculator

    @Before
    fun setup() {
        println("This will be executed before each test")
        calculator = Calculator()
    }

    @After
    fun tearDown() {
        println("This will be executed after each test")
    }

    @Test
    fun testAddition() {
        // Arrange
//        val calculator = Calculator()
        // Act
        val result = calculator.add(5, 5)
        // expected value: 10, actual value:?
        // Assert
        Assert.assertEquals(10, result)
    }

    @Test
    fun testSubtraction() {
//        val calculator = Calculator()
        val result = calculator.subtract(10, 5)
        Assert.assertEquals(5, result)
    }

    @Test
    fun testMultiplication() {
//        val calculator = Calculator()
        val result = calculator.multiply(5, 5)
        Assert.assertEquals(25, result)
    }

    @Test
    fun testDivision() {
        val result = calculator.divide(10, 2)
        Assert.assertEquals(5, result)
    }

    //    @Test
    @Test(expected = IllegalArgumentException::class)
    fun `dividing by zero should throw an exception`() {
        val result = calculator.divide(10, 0)
    }
}
