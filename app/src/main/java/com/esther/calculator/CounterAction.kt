package com.esther.calculator

sealed class CounterAction {
    data class IncreaseCount(val type: CounterType) : CounterAction()

    data class DecreaseCount(val type: CounterType) : CounterAction()

    object CopySecondaryToMain : CounterAction()

    object CopyMainToSecondary : CounterAction()

    object DeleteCounter : CounterAction()
}