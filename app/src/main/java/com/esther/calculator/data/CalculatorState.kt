package com.esther.calculator.data

data class CalculatorState(
    val result: String = "",
    val formula: String = "",
)

data class CalculatorUiState(
    var mainState: CalculatorState = CalculatorState(),
    var secondaryState: CalculatorState = CalculatorState(),
)