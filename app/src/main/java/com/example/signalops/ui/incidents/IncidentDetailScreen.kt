package com.example.signalops.ui.incidents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.example.signalops.ui.utils.TimeUtils

@Composable
fun IncidentDetailScreen(
    id: Long,
    title: String,
    severity: String,
    status: String,
    createdAt: String,
    onBack: () -> Unit,
    onStatusUpdated: () -> Unit,
    vm: IncidentDetailViewModel = viewModel()
) {
    val state by vm.state.collectAsState()

    LaunchedEffect(state.success) {
        if (state.success) {
            onStatusUpdated()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Incident Details",
            style = MaterialTheme.typography.headlineMedium
        )

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(10.dp))
                Text("Severity: $severity")
                Text("Status: $status")
                Text("Created: ${TimeUtils.formatTimestamp(createdAt)}")
            }
        }

        if (state.error != null) {
            Text(
                text = "Error: ${state.error}",
                color = MaterialTheme.colorScheme.error
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { vm.updateStatus(id, "INVESTIGATING") },
                enabled = !state.loading
            ) {
                Text("Acknowledge")
            }

            Button(
                onClick = { vm.updateStatus(id, "MONITORING") },
                enabled = !state.loading
            ) {
                Text("Monitoring")
            }
        }

        Button(
            onClick = { vm.updateStatus(id, "RESOLVED") },
            enabled = !state.loading
        ) {
            Text(if (state.loading) "Updating..." else "Resolve")
        }

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}