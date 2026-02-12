package com.example.signalops.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(onLogout: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SignalOps") },
                actions = {
                    TextButton(onClick = onLogout) { Text("Logout") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Dashboard", style = MaterialTheme.typography.headlineMedium)
            Text(
                "You’re logged in. Next we’ll show services, incidents, and alerts here.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(8.dp))

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
}
