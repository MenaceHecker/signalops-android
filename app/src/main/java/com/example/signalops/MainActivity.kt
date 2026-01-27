package com.example.signalops

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.signalops.app.auth.AuthGraph
import com.example.signalops.app.auth.authNavGraph
import com.example.signalops.app.navigation.AppRoutes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignalOpsApp()
        }
    }
}

@Composable
fun SignalOpsApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthGraph.ROUTE
    ) {
        authNavGraph(
            navController = navController,
            onAuthSuccess = {
                navController.navigate(AppRoutes.HOME) {
                    popUpTo(AuthGraph.ROUTE) { inclusive = true }
                    launchSingleTop = true
                }
            }
        )

        composable(AppRoutes.HOME) {
    //HomeScreen goes here
        }
    }
}
