package com.esther.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(CalculatorState())

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operation -> enterOperation(action.operator)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Percentage -> enterPercentage()
            is CalculatorAction.Sign -> enterSign()
            is CalculatorAction.Calculate -> calculate()
        }
    }

    private fun enterSign() {
        TODO("Not yet implemented")
    }

    private fun enterPercentage() {
        TODO("Not yet implemented")
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun calculate() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val result =
                when (state.operation) {
                    is CalculatorOperation.Add -> number1 + number2
                    is CalculatorOperation.Subtract -> number1 - number2
                    is CalculatorOperation.Multiply -> number1 * number2
                    is CalculatorOperation.Divide -> number1 / number2
                    null -> return
                }

            state =
                state.copy(
                    number1 = "",
                    number2 = "",
                    operation = null,
                )
        }
    }

    private fun enterDecimal() {
        if (state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
            state =
                state.copy(
                    number1 = state.number1 + ".",
                )
            return
        } else if (!state.number2.contains(".") && state.number2.isNotBlank()) {
            state =
                state.copy(
                    number2 = state.number2 + ".",
                )
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
//            if (state.number1.length >= MAX_NUM_LENGTH) {
//                return
//            }
            state =
                state.copy(
                    number1 = state.number1 + number,
                )
            return
        }
//        if (state.number2.length >= MAX_NUM_LENGTH) {
//            return
//        }
        state =
            state.copy(
                number2 = state.number2 + number,
            )
    }

    companion object {
//        private const val MAX_NUM_LENGTH = 8
    }
}