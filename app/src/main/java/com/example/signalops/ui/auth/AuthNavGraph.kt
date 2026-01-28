package com.example.signalops.ui.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
                onSignUpClick = { navController.navigate(AuthRoutes.SIGN_UP) },
                onLoginSuccess = onAuthSuccess
            )
        }
        composable(AuthRoutes.SIGN_UP) {
            SignUpScreen(
                onBackToLogin = { navController.popBackStack() },
                onSignUpSuccess = onAuthSuccess
            )
        }
    }
}