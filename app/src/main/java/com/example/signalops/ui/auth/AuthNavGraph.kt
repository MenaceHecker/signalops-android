package com.example.signalops.ui.auth

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

object AuthRoutes {
    const val LOGIN = "login"
    const val SIGN_UP = "signup"
}

@Composable
fun AuthNavGraph(
    navController: NavHostController,
    onAuthSuccess: () -> Unit
) {
    val vm: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AuthRoutes.LOGIN
    ) {
        composable(AuthRoutes.LOGIN) {
            LoginScreen(
                vm = vm,
                onNavigateToSignup = { navController.navigate(AuthRoutes.SIGN_UP) },
                onLoginSuccess = onAuthSuccess
            )
        }

        composable(AuthRoutes.SIGN_UP) {
            SignUpScreen(
                vm = vm,
                onNavigateBack = { navController.popBackStack() },
                onSignupSuccess = onAuthSuccess
            )
        }
    }
}
