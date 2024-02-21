package com.esther.calculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
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
    val calculatorActions =
        listOf(
            CalculatorAction.Clear,
            CalculatorAction.Sign,
            CalculatorAction.Percentage,
            CalculatorAction.Operation(CalculatorOperation.Divide),
            CalculatorAction.Number(7),
            CalculatorAction.Number(8),
            CalculatorAction.Number(9),
            CalculatorAction.Operation(CalculatorOperation.Multiply),
            CalculatorAction.Number(4),
            CalculatorAction.Number(5),
            CalculatorAction.Number(6),
            CalculatorAction.Operation(CalculatorOperation.Subtract),
            CalculatorAction.Number(1),
            CalculatorAction.Number(2),
            CalculatorAction.Number(3),
            CalculatorAction.Operation(CalculatorOperation.Add),
            CalculatorAction.Number(0),
            CalculatorAction.Decimal,
            CalculatorAction.Calculate,
        )

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
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.TopEnd,
            ) {
                Column {
                    AutoResizedText(
                        text = "${state.result}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 60.sp),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    AutoResizedText(
                        text = "${state.formula}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 40.sp),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                verticalArrangement = Arrangement.spacedBy(space / 2),
                horizontalArrangement = Arrangement.spacedBy(space / 2),
            ) {
                calculatorActions.fastForEachIndexed { i, action ->
                    item(span = {
                        if (i == 16) {
                            GridItemSpan(2)
                        } else {
                            GridItemSpan(1)
                        }
                    }) {
                        CalculatorButton(
                            symbol = action.symbol,
                            modifier =
                                Modifier
                                    .background(MaterialTheme.colorScheme.primary),
//                                    .aspectRatio(
//                                        if (i == 16) {
//                                            2.1f
//                                        } else {
//                                            1f
//                                        },
//                                    )
                            onClick = { viewModel.onAction(action) },
                        )
                    }
                }
            }
        }
    }
}