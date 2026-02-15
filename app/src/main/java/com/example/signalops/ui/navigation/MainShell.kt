package com.example.signalops.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.signalops.ui.dashboard.DashboardScreen
import com.example.signalops.ui.incidents.IncidentsScreen
import com.example.signalops.ui.settings.SettingsScreen

private object MainRoutes {
    const val DASHBOARD = "dashboard"
    const val INCIDENTS = "incidents"
    const val SETTINGS = "settings"
}

@Composable
fun MainShell(
    onLogout: () -> Unit
) {
    val navController = rememberNavController()
    val items = listOf(
        MainRoutes.DASHBOARD to Pair("Dashboard", Icons.Default.Home),
        MainRoutes.INCIDENTS to Pair("Incidents", Icons.Default.List),
        MainRoutes.SETTINGS to Pair("Settings", Icons.Default.Settings),
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val current = backStackEntry?.destination?.route

                items.forEach { (route, meta) ->
                    NavigationBarItem(
                        selected = current == route,
                        onClick = {
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(meta.second, contentDescription = meta.first) },
                        label = { Text(meta.first) }
                    )
                }
            }
        }
    ) { padding ->
        MainNavGraph(
            navController = navController,
            padding = padding,
            onLogout = onLogout
        )
    }
}

@Composable
private fun MainNavGraph(
    navController: NavHostController,
    padding: PaddingValues,
    onLogout: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = MainRoutes.DASHBOARD,
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        composable(MainRoutes.DASHBOARD) { DashboardScreen() }
        composable(MainRoutes.INCIDENTS) { IncidentsScreen() }
        composable(MainRoutes.SETTINGS) { SettingsScreen(onLogout = onLogout) }
    }
}
