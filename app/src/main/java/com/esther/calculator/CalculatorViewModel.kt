package com.esther.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.jairrab.calc.Calculator
import com.github.jairrab.calc.CalculatorButton
import com.github.jairrab.calc.CalculatorType
import com.github.jairrab.calc.CalculatorUpdate
import java.text.DecimalFormat

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(CalculatorState())
    val calculator = Calculator.getInstance(calculatorType = CalculatorType.BASIC_NON_MDAS)

    init {
        calculator.setListener { calculatorUpdate ->
            when (calculatorUpdate) {
                is CalculatorUpdate.Initializing -> state = CalculatorState()
                is CalculatorUpdate.OnUpdate -> {
                    if (calculatorUpdate.entries.isNotEmpty()) {
                        state =
                            state.copy(
                                result = if (calculatorUpdate.entries.last() == "=") calculatorUpdate.result.toDollarExpression() else "",
                                formula = calculatorUpdate.entries.joinToString(separator = "").replace("*", "×").replace("/", "÷"),
                            )
                    }
                }
                else -> return@setListener
            }
        }
    }

    private fun Double.toDollarExpression(): String {
        val formatter = DecimalFormat("#,###.########")
        return formatter.format(this)
    }

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Clear -> {
                calculator.clear()
                state = CalculatorState()
            }

            is CalculatorAction.Operation -> enterOperation(action.operator)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Percentage -> enterPercentage()
            is CalculatorAction.Sign -> enterSign()
            is CalculatorAction.Calculate -> calculate()
        }
    }

    private fun enterSign() {
        val value = calculator.getCurrentNumber() * -1
        calculator.resetToNumber(value, false)
        calculator.pressEquals()
    }

    private fun enterPercentage() {
        calculator.pressPercent()
    }

    private fun enterOperation(operation: CalculatorOperation) {
        when (operation) {
            CalculatorOperation.Add -> calculator.pressPlus()
            CalculatorOperation.Divide -> calculator.pressDivide()
            CalculatorOperation.Multiply -> calculator.pressMultiply()
            CalculatorOperation.Subtract -> calculator.pressMinus()
        }
    }

    private fun calculate() {
        calculator.pressEquals()
    }

    private fun enterDecimal() {
        calculator.pressDecimal()
    }

    private fun enterNumber(number: Int) {
        CalculatorButton.values().map {
            if (it.tag == number.toString()) calculator.press(it)
        }
    }
}
