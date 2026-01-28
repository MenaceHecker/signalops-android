package com.example.signalops

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.signalops.ui.auth.AuthNavGraph

@Composable
fun SignalOpsApp() {
    val navController = rememberNavController()

    AuthNavGraph(
        navController = navController,
        onAuthSuccess = {
            // TODO later: navigate to dashboard/home graph
        }
    )
}
