package com.example.signalops.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.signalops.ui.main.ProfileViewModel
import com.example.signalops.ui.utils.TimeUtils

@Composable
fun DashboardScreen(
    profileVm: ProfileViewModel = viewModel(),
    dashboardVm: DashboardViewModel = viewModel()
) {
    val profileState by profileVm.state.collectAsState()
    val dashboardState by dashboardVm.state.collectAsState()

    LaunchedEffect(Unit) {
        profileVm.loadProfile()
        dashboardVm.loadSummary()
    }

    val totalIncidents = dashboardState.incidents.size
    val investigatingCount = dashboardState.incidents.count { it.status.equals("INVESTIGATING", ignoreCase = true) }
    val highSeverityCount = dashboardState.incidents.count { it.severity.equals("HIGH", ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.headlineMedium
        )

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Signed-in User",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(8.dp))

                when {
                    profileState.loading -> {
                        Text("Loading profile...")
                    }

                    profileState.error != null -> {
                        Text(
                            text = "Error: ${profileState.error}",
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    profileState.profile != null -> {
                        Text("Email: ${profileState.profile!!.email}")
                        Text("Role: ${profileState.profile!!.role}")
                        Text(
                            "Joined: ${
                                TimeUtils.formatTimestamp(profileState.profile!!.createdAt)
                            }"
                        )
                    }

                    else -> {
                        Text("No profile data available")
                    }
                }
            }
        }

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Incident Summary",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(8.dp))

                when {
                    dashboardState.loading -> {
                        Text("Loading summary...")
                    }

                    dashboardState.error != null -> {
                        Text(
                            text = "Error: ${dashboardState.error}",
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    else -> {
                        Text("Total incidents: $totalIncidents")
                        Text("Investigating: $investigatingCount")
                        Text("High severity: $highSeverityCount")
                    }
                }
            }
        }

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "System Status",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(6.dp))
                Text("All services healthy (placeholder)")
            }
        }
    }
}