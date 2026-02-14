package com.example.signalops.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.signalops.ui.auth.AuthRoutes
import com.example.signalops.ui.auth.authGraph

object AppRoutes {
    const val MAIN = "main"
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AuthRoutes.ROOT
    ) {
        authGraph(
            navController = navController,
            onAuthSuccess = {
                navController.navigate(AppRoutes.MAIN) {
                    popUpTo(AuthRoutes.ROOT) { inclusive = true }
                    launchSingleTop = true
                }
            }
        )

        composable(AppRoutes.MAIN) {
            MainShell(
                onLogout = {
                    navController.navigate(AuthRoutes.ROOT) {
                        popUpTo(AppRoutes.MAIN) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
