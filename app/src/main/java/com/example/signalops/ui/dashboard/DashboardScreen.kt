package com.example.signalops.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Dashboard", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Quick overview of services + incidents.",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Services", style = MaterialTheme.typography.titleMedium)
                Text("0 healthy • 0 degraded • 0 down", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Incidents", style = MaterialTheme.typography.titleMedium)
                Text("No active incidents", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
