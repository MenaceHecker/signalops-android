package com.example.signalops

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.signalops.ui.navigation.AppNavGraph

@Composable
fun SignalOpsApp() {
    val navController = rememberNavController()
    AppNavGraph(navController)
}
