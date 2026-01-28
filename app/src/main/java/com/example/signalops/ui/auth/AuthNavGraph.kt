package com.example.signalops.ui.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

object AuthRoutes {
    const val LOGIN = "login"
    const val SIGNUP = "signup"
}

@Composable
fun AuthNavGraph(
    navController: NavHostController,
    onAuthSuccess: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = AuthRoutes.LOGIN
    ) {
        composable(AuthRoutes.LOGIN) {
            LoginScreen(
                onLoginSuccess = { onAuthSuccess() },
                onNavigateToSignup = { navController.navigate(AuthRoutes.SIGNUP) }
            )
        }

        composable(AuthRoutes.SIGNUP) {
            SignUpScreen(
                onNavigateBack = { navController.popBackStack() },
                onSignupSuccess = { onAuthSuccess() }
            )
        }
    }
}
