package com.example.signalops.ui.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.signalops.data.AuthRepository
import com.example.signalops.data.local.TokenStore
import com.example.signalops.data.remote.ApiClient

object AuthRoutes {
    const val LOGIN = "login"
    const val SIGN_UP = "signup"
}

@Composable
fun AuthNavGraph(
    navController: NavHostController,
    onAuthSuccess: () -> Unit
) {
    val context = LocalContext.current
    val repo = remember { AuthRepository(ApiClient.authApi, TokenStore(context)) }
    val vm = remember { AuthViewModel(repo) }

    NavHost(navController = navController, startDestination = AuthRoutes.LOGIN) {

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
