package com.esther.calculator

/*
 * r1 C,+/-,%, ÷
 * r2 7, 8, 9, ×
 * r3 4, 5, 6, -
 * r4 1, 2, 3, +
 * r5 0, ., =
 * */
sealed class CalculatorAction(val symbol: String) {
    data class Number(val number: Int) : CalculatorAction(number.toString())

    object Clear : CalculatorAction("C")

    object Decimal : CalculatorAction(".")

    object Calculate : CalculatorAction("=")

    object Percentage : CalculatorAction("%")

    object Sign : CalculatorAction("+/-")

    data class Operation(val operator: CalculatorOperation) : CalculatorAction(operator.symbol)
}
