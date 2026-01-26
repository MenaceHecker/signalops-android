package com.example.signalops.app.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.signalops.ui.auth.LoginScreen

@Composable
fun AuthNavGraph(
    navController: NavHostController,
    startDestination: String = AuthRoutes.LOGIN,
    onAuthSuccess: () -> Unit = {}
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AuthRoutes.LOGIN) {
            LoginScreen(
                onNavigateToSignup = {
                    navController.navigate(AuthRoutes.SIGNUP)
                },
                onLoginSuccess = onAuthSuccess
            )
        }

        composable(AuthRoutes.SIGNUP) {
            SignupScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onSignupSuccess = onAuthSuccess
            )
        }
    }
}
