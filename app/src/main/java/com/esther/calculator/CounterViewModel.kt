package com.esther.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    var stateMap =
        mutableStateMapOf(
            CounterType.Main to CounterState(),
            CounterType.Secondary to CounterState(),
        )

    fun onAction(
        type: CounterType,
        action: CounterAction,
    ) {
        when (action) {
            is CounterAction.CopySecondaryToMain -> {
                stateMap[CounterType.Main] = stateMap[CounterType.Secondary] ?: CounterState()
            }
            is CounterAction.CopyMainToSecondary -> {
                stateMap[CounterType.Secondary] = stateMap[CounterType.Main] ?: CounterState()
            }
            is CounterAction.DeleteCounter -> {
                stateMap[CounterType.Main] = CounterState()
                stateMap[CounterType.Secondary] = CounterState()
            }
            is CounterAction.DecreaseCount, is CounterAction.IncreaseCount -> {
                updateCount(type, action == CounterAction.IncreaseCount)
            }
        }
    }

    private fun updateCount(
        type: CounterType,
        isIncrease: Boolean,
    ) {
        var currentState = stateMap[type] ?: CounterState()
        val update = if (isIncrease) 1 else -1

        currentState =
            currentState.copy(
                number = currentState.number + update,
                time = currentState.time + 1,
            )
        stateMap[type] = currentState
    }
}
