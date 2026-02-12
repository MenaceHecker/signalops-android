package com.example.signalops.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.signalops.ui.auth.AuthNavGraph
import com.example.signalops.ui.dashboard.DashboardScreen

object AppRoutes {
    const val AUTH = "auth"
    const val DASHBOARD = "dashboard"
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.AUTH
    ) {
        composable(AppRoutes.AUTH) {
            AuthNavGraph(
                navController = navController,
                onAuthSuccess = {
                    navController.navigate(AppRoutes.DASHBOARD) {
                        popUpTo(AppRoutes.AUTH) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(AppRoutes.DASHBOARD) {
            DashboardScreen(
                onLogout = {
                    navController.navigate(AppRoutes.AUTH) {
                        popUpTo(AppRoutes.DASHBOARD) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
