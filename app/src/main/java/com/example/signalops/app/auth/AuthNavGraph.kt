package com.example.signalops.app.auth

import androidx.navigation.NavHostController
import androidx.navigation.navigation
import androidx.navigation.compose.composable
import androidx.navigation.NavGraphBuilder
import com.example.signalops.ui.auth.LoginScreen
import com.example.signalops.ui.auth.SignupScreen

// Putting a Nested graph route
object AuthGraph {
    const val ROUTE = "auth"
}

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    onAuthSuccess: () -> Unit,
) {
    navigation(
        startDestination = AuthRoutes.LOGIN,
        route = AuthGraph.ROUTE
    ) {
        composable(AuthRoutes.LOGIN) {
            LoginScreen(
                onNavigateToSignup = { navController.navigate(AuthRoutes.SIGNUP) },
                onLoginSuccess = onAuthSuccess
            )
        }

        composable(AuthRoutes.SIGNUP) {
            SignupScreen(
                onNavigateBack = { navController.popBackStack() },
                onSignupSuccess = onAuthSuccess
            )
        }
    }
}
