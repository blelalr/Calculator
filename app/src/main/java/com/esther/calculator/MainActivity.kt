package com.esther.calculator

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.esther.calculator.ui.Calculator
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
                    val configuration = LocalConfiguration.current
                    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        // landscape mode
                    } else if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                        // portrait mode
                        Calculator()
                    }
                }
            }
        }
    }
}
