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

class CalculatorViewModel : ViewModel() {
    private var mState by mutableStateOf(CalculatorUiState())
    private val mainCalculator by lazy { buildCalculator(true) }
    private val secondaryCalculator by lazy { buildCalculator(false) }

    private fun buildCalculator(isMain: Boolean): Calculator {
        return CalculatorBuilder().listener { calculatorUpdate ->
            when (calculatorUpdate) {
                is CalculatorUpdate.OnUpdate -> {
                    if (calculatorUpdate.entries.isNotEmpty()) {
                        updateState(
                            isMain,
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
        isMain: Boolean,
    ) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number, isMain)
            is CalculatorAction.Clear -> {
                getCalculator(isMain).clear()
                updateState(isMain, CalculatorState())
            }

            is CalculatorAction.Operation -> enterOperation(action.operator, isMain)
            is CalculatorAction.Decimal -> getCalculator(isMain).pressDecimal()
            is CalculatorAction.Percentage -> getCalculator(isMain).pressPercent()
            is CalculatorAction.Sign -> enterSign(isMain)
            is CalculatorAction.Calculate -> getCalculator(isMain).pressEquals()
        }
    }

    private fun updateState(
        isMain: Boolean,
        calculatorState: CalculatorState,
    ) {
        mState =
            if (isMain) {
                mState.copy(mainState = calculatorState)
            } else {
                mState.copy(secondaryState = calculatorState)
            }
    }

    private fun getCalculator(isMain: Boolean): Calculator {
        return if (isMain) mainCalculator else secondaryCalculator
    }

    private fun enterSign(isMain: Boolean) {
        val value = getCalculator(isMain).getCurrentNumber() * -1
        getCalculator(isMain)
            .resetToNumber(
                number = value,
                readyToClear = false,
            )
        updateState(
            isMain,
            CalculatorState(
                formula = value.toDollarExpression(false),
            ),
        )
    }

    private fun enterOperation(
        operation: CalculatorOperation,
        isMain: Boolean,
    ) {
        when (operation) {
            CalculatorOperation.Add -> getCalculator(isMain).pressPlus()
            CalculatorOperation.Divide -> getCalculator(isMain).pressDivide()
            CalculatorOperation.Multiply -> getCalculator(isMain).pressMultiply()
            CalculatorOperation.Subtract -> getCalculator(isMain).pressMinus()
        }
    }

    private fun enterNumber(
        number: Int,
        isMain: Boolean,
    ) {
        CalculatorButton.values().map {
            if (it.tag == number.toString()) getCalculator(isMain).press(it)
        }
    }

    fun onResult(isMain: Boolean): String {
        return if (isMain) mState.mainState.result else mState.secondaryState.result
    }

    fun onFormula(isMain: Boolean): String {
        return if (isMain) mState.mainState.formula else mState.secondaryState.formula
    }
}
