package com.esther.calculator

/*
 * r1 C,+/-,%, ÷
 * r2 7, 8, 9, ×
 * r3 4, 5, 6, -
 * r4 1, 2, 3, +
 * r5 0, ., =
 * */
sealed class CalculatorOperation(val symbol: String, val operator: String) {
    object Add : CalculatorOperation("+", "+")

    object Subtract : CalculatorOperation("-", "-")

    object Multiply : CalculatorOperation("×", "*")

    object Divide : CalculatorOperation("÷", "/")
}
