package com.example.signalops.ui.incidents

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IncidentsScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("Incidents", style = MaterialTheme.typography.headlineMedium)
        Text("Active + past incidents will show up here.", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
