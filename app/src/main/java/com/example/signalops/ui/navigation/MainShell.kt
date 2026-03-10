package com.example.signalops.ui.navigation

import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.signalops.ui.dashboard.DashboardScreen
import com.example.signalops.ui.incidents.CreateIncidentScreen
import com.example.signalops.ui.incidents.IncidentDetailScreen
import com.example.signalops.ui.incidents.IncidentsScreen
import com.example.signalops.ui.settings.SettingsScreen

private object MainRoutes {
    const val DASHBOARD = "dashboard"
    const val INCIDENTS = "incidents"
    const val SETTINGS = "settings"
    const val INCIDENT_DETAIL = "incident_detail"
    const val CREATE_INCIDENT = "create_incident"
}

@Composable
fun MainShell(
    onLogout: () -> Unit
) {
    val navController = rememberNavController()

    val items = listOf(
        MainRoutes.DASHBOARD to Pair("Dashboard", Icons.Default.Home),
        MainRoutes.INCIDENTS to Pair("Incidents", Icons.Default.List),
        MainRoutes.SETTINGS to Pair("Settings", Icons.Default.Settings)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route

                items.forEach { (route, meta) ->
                    NavigationBarItem(
                        selected = currentRoute == route,
                        onClick = {
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
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
        composable(MainRoutes.DASHBOARD) {
            DashboardScreen()
        }

        composable(MainRoutes.INCIDENTS) {
            IncidentsScreen(
                onIncidentClick = { title, severity, status, createdAt ->
                    navController.navigate(
                        "${MainRoutes.INCIDENT_DETAIL}/" +
                                "${Uri.encode(title)}/" +
                                "${Uri.encode(severity)}/" +
                                "${Uri.encode(status)}/" +
                                "${Uri.encode(createdAt)}"
                    )
                },
                onCreateIncident = {
                    navController.navigate(MainRoutes.CREATE_INCIDENT)
                }
            )
        }

        composable(MainRoutes.SETTINGS) {
            SettingsScreen(onLogout = onLogout)
        }

        composable(MainRoutes.CREATE_INCIDENT) {
            CreateIncidentScreen(
                onBack = { navController.popBackStack() },
                onCreated = { navController.popBackStack() }
            )
        }

        composable(
            route = "${MainRoutes.INCIDENT_DETAIL}/{title}/{severity}/{status}/{createdAt}"
        ) { backStackEntry ->
            val title = Uri.decode(backStackEntry.arguments?.getString("title").orEmpty())
            val severity = Uri.decode(backStackEntry.arguments?.getString("severity").orEmpty())
            val status = Uri.decode(backStackEntry.arguments?.getString("status").orEmpty())
            val createdAt = Uri.decode(backStackEntry.arguments?.getString("createdAt").orEmpty())

            IncidentDetailScreen(
                title = title,
                severity = severity,
                status = status,
                createdAt = createdAt,
                onBack = { navController.popBackStack() }
            )
        }
    }
}