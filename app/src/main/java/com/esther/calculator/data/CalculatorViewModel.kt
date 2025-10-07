package com.esther.calculator.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.esther.calculator.utils.CalculatorBuilder
import com.github.jairrab.calc.Calculator
import com.github.jairrab.calc.CalculatorButton
import com.github.jairrab.calc.CalculatorUpdate
import java.text.DecimalFormat

sealed class CalculatorId {
    object Primary : CalculatorId()

    object Secondary : CalculatorId()
}

class CalculatorViewModel : ViewModel() {
    private var mState by mutableStateOf(CalculatorState())
    private var mSecondaryState by mutableStateOf(CalculatorState())

    private val calculator by lazy { buildCalculator(CalculatorId.Primary) }
    private val secondaryCalculator by lazy { buildCalculator(CalculatorId.Secondary) }

    private fun buildCalculator(id: CalculatorId): Calculator {
        return CalculatorBuilder().listener { calculatorUpdate ->
            when (calculatorUpdate) {
                is CalculatorUpdate.OnUpdate -> {
                    if (calculatorUpdate.entries.isNotEmpty()) {
                        updateState(
                            id,
                            CalculatorState(
                                result =
                                    if (calculatorUpdate.entries.last() == "=") {
                                        calculatorUpdate.result.toDollarExpression(true)
                                    } else {
                                        ""
                                    },
                                formula =
                                    calculatorUpdate.entries
                                        .joinToString(separator = "")
                                        .replace("*", "×")
                                        .replace("/", "÷"),
                            ),
                        )
                    }
                }

                else -> return@listener
            }
        }.build()
    }

    private fun Double.toDollarExpression(hasComma: Boolean): String {
        val formatter =
            if (hasComma) {
                DecimalFormat("#,###.########")
            } else {
                DecimalFormat("#.########")
            }
        return formatter.format(this)
    }

    fun onAction(
        action: CalculatorAction,
        isPrimary: Boolean,
    ) {
        val targetCalculator = if (isPrimary) calculator else secondaryCalculator
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number, target = targetCalculator)
            is CalculatorAction.Clear -> {
                targetCalculator.clear()
                updateState(id = if (isPrimary) CalculatorId.Primary else CalculatorId.Secondary, CalculatorState())
            }

            is CalculatorAction.Operation -> enterOperation(action.operator, target = targetCalculator)
            is CalculatorAction.Decimal -> targetCalculator.pressDecimal()
            is CalculatorAction.Percentage -> targetCalculator.pressPercent()
            is CalculatorAction.Sign ->
                enterSign(
                    id = if (isPrimary) CalculatorId.Primary else CalculatorId.Secondary,
                    target = targetCalculator,
                )
            is CalculatorAction.Calculate -> targetCalculator.pressEquals()
        }
    }

    private fun updateState(
        id: CalculatorId,
        calculatorState: CalculatorState,
    ) {
        when (id) {
            is CalculatorId.Primary -> mState = calculatorState
            is CalculatorId.Secondary -> mSecondaryState = calculatorState
        }
    }

    private fun enterSign(
        target: Calculator,
        id: CalculatorId,
    ) {
        val value = target.getCurrentNumber() * -1
        target
            .resetToNumber(
                number = value,
                readyToClear = false,
            )
        updateState(
            id,
            CalculatorState(
                formula = value.toDollarExpression(false),
            ),
        )
    }

    private fun enterOperation(
        operation: CalculatorOperation,
        target: Calculator,
    ) {
        when (operation) {
            CalculatorOperation.Add -> target.pressPlus()
            CalculatorOperation.Divide -> target.pressDivide()
            CalculatorOperation.Multiply -> target.pressMultiply()
            CalculatorOperation.Subtract -> target.pressMinus()
        }
    }

    private fun enterNumber(
        number: Int,
        target: Calculator,
    ) {
        CalculatorButton.values().map {
            if (it.tag == number.toString()) target.press(it)
        }
    }

    fun onResult(id: CalculatorId): String {
        return when (id) {
            is CalculatorId.Primary -> mState.result
            is CalculatorId.Secondary -> mSecondaryState.result
        }
    }

    fun onFormula(id: CalculatorId): String {
        return when (id) {
            is CalculatorId.Primary -> mState.formula
            is CalculatorId.Secondary -> mSecondaryState.formula
        }
    }
}
