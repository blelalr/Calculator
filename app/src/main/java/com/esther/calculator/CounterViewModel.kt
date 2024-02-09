package com.esther.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private var mStateMap =
        mutableMapOf(
            CounterType.Main to mutableStateOf(CounterState()),
            CounterType.Secondary to mutableStateOf(CounterState()),
        )

    val main by mStateMap[CounterType.Main] ?: mutableStateOf(CounterState())
    val secondary by mStateMap[CounterType.Secondary] ?: mutableStateOf(CounterState())

    fun onAction(
        type: CounterType,
        action: CounterAction,
    ) {
        when (action) {
            is CounterAction.CopySecondaryToMain, CounterAction.CopyMainToSecondary -> {
                copyState(type, action == CounterAction.CopySecondaryToMain)
            }

            is CounterAction.DeleteCounter -> {
                mStateMap[CounterType.Main]?.value = CounterState()
                mStateMap[CounterType.Secondary]?.value = CounterState()
            }

            is CounterAction.DecreaseCount, is CounterAction.IncreaseCount -> {
                updateCount(type, action == CounterAction.IncreaseCount)
            }
        }
    }

    private fun copyState(
        type: CounterType,
        isCopyToMain: Boolean,
    ) {
        mStateMap[type]?.value = if (isCopyToMain) secondary else main
    }

    private fun updateCount(
        type: CounterType,
        isIncrease: Boolean,
    ) {
        val update = if (isIncrease) 1 else -1
        var temp = mStateMap[type]?.value ?: CounterState()
        temp =
            temp.copy(
                number = temp.number + update,
                time = temp.time + 1,
            )
        mStateMap[type]?.value = temp
    }
}
