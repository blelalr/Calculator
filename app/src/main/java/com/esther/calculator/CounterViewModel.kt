package com.esther.calculator

import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    fun onAction(
        state: CounterState,
        action: CounterAction,
    ): CounterState {
        return when (action) {
            is CounterAction.CopySecondaryToMain -> {
                state
            }
            is CounterAction.CopyMainToSecondary -> {
                state
            }
            is CounterAction.DeleteCounter -> {
                CounterState()
            }
            is CounterAction.DecreaseCount -> {
                state.copy(
                    number = state.number - 1,
                    time = state.time + 1,
                )
            }
            is CounterAction.IncreaseCount -> {
                state.copy(
                    number = state.number + 1,
                    time = state.time + 1,
                )
            }
        }
    }
}
