package com.example.signalops.ui.auth

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

object AuthRoutes {
    const val ROOT = "auth"
    const val LOGIN = "login"
    const val SIGN_UP = "signup"
}

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    onAuthSuccess: () -> Unit
) {
    navigation(
        startDestination = AuthRoutes.LOGIN,
        route = AuthRoutes.ROOT
    ) {
        composable(AuthRoutes.LOGIN) {
            val vm: AuthViewModel = viewModel()
            LoginScreen(
                vm = vm,
                onNavigateToSignup = { navController.navigate(AuthRoutes.SIGN_UP) },
                onLoginSuccess = onAuthSuccess
            )
        }

        composable(AuthRoutes.SIGN_UP) {
            val vm: AuthViewModel = viewModel()
            SignUpScreen(
                vm = vm,
                onNavigateBack = { navController.popBackStack() },
                onSignupSuccess = onAuthSuccess
            )
        }
    }
}
