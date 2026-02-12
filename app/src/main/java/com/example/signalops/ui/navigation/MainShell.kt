package com.example.signalops.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.signalops.ui.dashboard.DashboardScreen
import com.example.signalops.ui.incidents.IncidentsScreen
import com.example.signalops.ui.services.ServicesScreen
import com.example.signalops.ui.settings.SettingsScreen

@Composable
fun MainShell(onLogout: () -> Unit) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    val selected = currentRoute == item.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(
                                item.route,
                                navOptions {
                                    popUpTo(BottomNavItem.Dashboard.route) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            )
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Dashboard.route
        ) {
            composable(BottomNavItem.Dashboard.route) { DashboardScreen() }
            composable(BottomNavItem.Services.route) { ServicesScreen() }
            composable(BottomNavItem.Incidents.route) { IncidentsScreen() }
            composable(BottomNavItem.Settings.route) { SettingsScreen(onLogout) }
        }
    }
}