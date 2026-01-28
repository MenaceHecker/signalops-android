package com.example.signalops.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.signalops.ui.auth.AuthNavGraph
import com.example.signalops.ui.dashboard.Dashboard

object AppRoutes {
    const val AUTH = "auth"
    const val DASHBOARD = "dashboard"
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.AUTH
    ) {
        composable(AppRoutes.AUTH) {
            AuthNavGraph(
                navController = navController,
                onAuthSuccess = {
                    navController.navigate(AppRoutes.DASHBOARD) {
                        // Remove auth from backstack so back doesn't return to login/signup
                        popUpTo(AppRoutes.AUTH) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoutes.DASHBOARD) {
            Dashboard()
        }
    }
}
