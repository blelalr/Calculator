package com.esther.calculator

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.esther.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    OrientationSwapper(
                        landscapeContent = {
                            CounterScreen(Modifier.weight(4f), Modifier.weight(1f), Modifier.weight(4f))
                        },
                        portraitContent = {
                            CounterScreen(Modifier.weight(4f), Modifier.weight(1f), Modifier.weight(4f))
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun OrientationSwapper(
    portraitContent: @Composable ColumnScope.() -> Unit,
    landscapeContent: @Composable RowScope.() -> Unit,
) {
    val portrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    if (portrait) {
        Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxSize()) {
            portraitContent()
        }
    } else {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxSize()) {
            landscapeContent()
        }
    }
}

@Composable
fun CounterScreen(
    weight1: Modifier,
    weight2: Modifier,
    weight3: Modifier,
) {
    val viewModel = viewModel<CounterViewModel>()
    var mainState by remember {
        mutableStateOf(CounterState())
    }
    var secondaryState by remember {
        mutableStateOf(CounterState())
    }
    Box(modifier = weight1) {
        Counter(
            mainState,
            onIncreaseCounter = {
                mainState =
                    viewModel.onAction(
                        mainState,
                        CounterAction.IncreaseCount,
                    )
            },
            onDecreaseCounter = {
                viewModel.onAction(
                    mainState,
                    CounterAction.DecreaseCount,
                )
            },
        )
    }
    Box(modifier = weight2) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Button(
                onClick = {
                    mainState =
                        viewModel.onAction(
                            secondaryState,
                            CounterAction.CopySecondaryToMain,
                        )
                },
            ) {
                Text("<-")
            }
            Button(
                onClick = {
                    secondaryState =
                        viewModel.onAction(
                            mainState,
                            CounterAction.CopyMainToSecondary,
                        )
                },
            ) {
                Text("->")
            }
            Button(
                onClick = {
                    mainState =
                        viewModel.onAction(
                            mainState,
                            CounterAction.DeleteCounter,
                        )
                    secondaryState =
                        viewModel.onAction(
                            secondaryState,
                            CounterAction.DeleteCounter,
                        )
                },
            ) {
                Text("Del")
            }
        }
    }
    Box(modifier = weight3) {
        Counter(
            secondaryState,
            onIncreaseCounter = {
                secondaryState =
                    viewModel.onAction(
                        secondaryState,
                        CounterAction.IncreaseCount,
                    )
            },
            onDecreaseCounter = {
                secondaryState =
                    viewModel.onAction(
                        secondaryState,
                        CounterAction.DecreaseCount,
                    )
            },
        )
    }
}

@Composable
fun Counter(
    counterState: CounterState,
    onIncreaseCounter: () -> Unit,
    onDecreaseCounter: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Button(
            onClick = onDecreaseCounter,
        ) {
            Text(
                text = " - ",
                fontSize = 30.sp,
            )
        }
        Text(
            text = counterState.number.toString(),
            color = Color.Black,
            fontSize = 30.sp,
        )
        Button(
            onClick = onIncreaseCounter,
        ) {
            Text(
                text = " + ",
                fontSize = 30.sp,
            )
        }
    }
}

data class CounterState(val number: Int = 0, val time: Int = 0)
