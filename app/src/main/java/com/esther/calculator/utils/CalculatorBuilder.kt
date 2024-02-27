package com.esther.calculator.utils

import com.github.jairrab.calc.Calculator
import com.github.jairrab.calc.CalculatorType

class CalculatorBuilder {
    private var listener: Calculator.Listener? = null

    fun listener(listener: Calculator.Listener): CalculatorBuilder {
        this.listener = listener
        return this
    }

    fun build(): Calculator {
        val calculator = Calculator.getInstance(CalculatorType.BASIC_NON_MDAS)
        listener?.let { calculator.setListener(it) }
        return calculator
    }
}
