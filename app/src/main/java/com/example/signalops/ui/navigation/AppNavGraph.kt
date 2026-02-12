package com.example.signalops.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.signalops.ui.auth.AuthNavGraph

object AppRoutes {
    const val AUTH = "auth"
    const val MAIN = "main"
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
                    navController.navigate(AppRoutes.MAIN) {
                        popUpTo(AppRoutes.AUTH) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(AppRoutes.MAIN) {
            MainShell(
                onLogout = {
                    navController.navigate(AppRoutes.AUTH) {
                        popUpTo(AppRoutes.MAIN) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
