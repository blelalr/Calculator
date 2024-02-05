package com.esther.calculator

sealed class CalculatorAction {
    data class Number(val number: Int) : CalculatorAction()

    object Clear : CalculatorAction()

    object Decimal : CalculatorAction()

    object Calculate : CalculatorAction()

    object Percentage : CalculatorAction()

    object Sign : CalculatorAction()

    data class Operation(val operator: CalculatorOperation) : CalculatorAction()
}
