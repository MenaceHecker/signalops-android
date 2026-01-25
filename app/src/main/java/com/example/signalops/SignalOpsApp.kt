package com.example.signalops

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.signalops.ui.navigation.AppNavGraph

@Composable
fun SignalOpsApp() {
    MaterialTheme {
        AppNavGraph()
    }
}
