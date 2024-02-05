package com.esther.calculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.esther.calculator.CalculatorAction
import com.esther.calculator.CalculatorButton
import com.esther.calculator.CalculatorOperation
import com.esther.calculator.CalculatorViewModel

@Composable
fun Calculator() {
    /*
     * r1 C,+/-,%, ÷
     * r2 7, 8, 9, ×
     * r3 4, 5, 6, -
     * r4 1, 2, 3, +
     * r5 0, ., =
     * */
    val space = 16.dp

    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(space)
                .background(MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxHeight()
                    .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(space / 2),
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(80.dp),
            ) {
                val ope =
                    when (state.operation) {
                        CalculatorOperation.Add -> "+"
                        CalculatorOperation.Subtract -> "-"
                        CalculatorOperation.Multiply -> "×"
                        CalculatorOperation.Divide -> "÷"
                        null -> ""
                    }
                AutoResizedText(
                    text = state.number1 + ope + state.number2,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 80.sp),
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space / 2),
            ) {
                CalculatorButton(
                    symbol = "C",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Clear)
                    },
                )
                CalculatorButton(
                    symbol = "+/-",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Sign)
                    },
                )
                CalculatorButton(
                    symbol = "%",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Percentage)
                    },
                )
                CalculatorButton(
                    symbol = "÷",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                    },
                )
            }
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space / 2),
            ) {
                CalculatorButton(
                    symbol = "7",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(7))
                    },
                )
                CalculatorButton(
                    symbol = "8",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(8))
                    },
                )
                CalculatorButton(
                    symbol = "9",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(9))
                    },
                )
                CalculatorButton(
                    symbol = "×",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                    },
                )
            }
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space / 2),
            ) {
                CalculatorButton(
                    symbol = "4",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(4))
                    },
                )
                CalculatorButton(
                    symbol = "5",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(5))
                    },
                )
                CalculatorButton(
                    symbol = "6",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(6))
                    },
                )
                CalculatorButton(
                    symbol = "-",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                    },
                )
            }
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space / 2),
            ) {
                CalculatorButton(
                    symbol = "1",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(1))
                    },
                )
                CalculatorButton(
                    symbol = "2",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(2))
                    },
                )
                CalculatorButton(
                    symbol = "3",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(3))
                    },
                )
                CalculatorButton(
                    symbol = "+",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                    },
                )
            }
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space / 2),
            ) {
                CalculatorButton(
                    symbol = "0",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(2f)
                            .weight(2f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Number(0))
                    },
                )
                CalculatorButton(
                    symbol = ".",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Decimal)
                    },
                )
                CalculatorButton(
                    symbol = "=",
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .aspectRatio(1f)
                            .weight(1f),
                    onClick = {
                        viewModel.onAction(CalculatorAction.Calculate)
                    },
                )
            }
        }
    }
}