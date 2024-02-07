package com.esther.calculator

sealed class CounterAction {
    object IncreaseCount : CounterAction()

    object DecreaseCount : CounterAction()

    object CopySecondaryToMain : CounterAction()

    object CopyMainToSecondary : CounterAction()

    object DeleteCounter : CounterAction()
}