package com.esther.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    var mainState by mutableStateOf(CounterState())
    var secondaryState by mutableStateOf(CounterState())

    fun onAction(action: CounterAction) {
        when (action) {
            is CounterAction.CopySecondaryToMain -> {
                mainState = secondaryState
            }
            is CounterAction.CopyMainToSecondary -> {
                secondaryState = mainState
            }
            is CounterAction.DeleteCounter -> {
                mainState = CounterState()
                secondaryState = CounterState()
            }
            is CounterAction.DecreaseCount -> {
                val isMain = action.type == CounterType.Main
                if (isMain) {
                    mainState = handleDecrease(mainState)
                } else {
                    secondaryState = handleDecrease(secondaryState)
                }
            }
            is CounterAction.IncreaseCount -> {
                val isMain = action.type == CounterType.Main
                if (isMain) {
                    mainState = handleIncrease(mainState)
                } else {
                    secondaryState = handleIncrease(secondaryState)
                }
            }
        }
    }

    private fun handleDecrease(state: CounterState): CounterState {
        return state.copy(
            number = state.number - 1,
            time = state.time + 1,
        )
    }

    private fun handleIncrease(state: CounterState): CounterState {
        return state.copy(
            number = state.number + 1,
            time = state.time + 1,
        )
    }
}
