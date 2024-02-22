package com.esther.calculator

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.esther.calculator.ui.CalculatorView
import com.esther.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val orientation = LocalConfiguration.current.orientation

                    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                        // portrait mode
                        CalculatorView(true)
                    } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        // landscape mode
                        Row {
                            Box(modifier = Modifier.weight(4f)) {
                                CalculatorView(false)
                            }
                            Box(modifier = Modifier.weight(1f))

                            Box(modifier = Modifier.weight(4f)) {
                                CalculatorView(false)
                            }
                        }
                    }
                }
            }
        }
    }
}