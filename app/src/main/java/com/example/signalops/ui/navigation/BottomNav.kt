package com.example.signalops.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Dashboard : BottomNavItem("tab_dashboard", "Dashboard", Icons.Filled.Dashboard)
    data object Services : BottomNavItem("tab_services", "Services", Icons.Filled.ListAlt)
    data object Incidents : BottomNavItem("tab_incidents", "Incidents", Icons.Filled.Warning)
    data object Settings : BottomNavItem("tab_settings", "Settings", Icons.Filled.Settings)
}

val bottomNavItems = listOf(
    BottomNavItem.Dashboard,
    BottomNavItem.Services,
    BottomNavItem.Incidents,
    BottomNavItem.Settings
)
