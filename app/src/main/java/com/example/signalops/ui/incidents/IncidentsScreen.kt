package com.example.signalops.ui.incidents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.signalops.ui.utils.TimeUtils

@Composable
fun IncidentsScreen(
    onIncidentClick: (id: Long, title: String, severity: String, status: String, createdAt: String) -> Unit,
    onCreateIncident: () -> Unit,
    vm: IncidentViewModel = viewModel()
) {
    val state by vm.state.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                vm.loadIncidents()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    when {
        state.loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text("Incidents", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(12.dp))
                Button(onClick = onCreateIncident) {
                    Text("New Incident")
                }
                Spacer(Modifier.height(12.dp))
                Text("Loading incidents...")
            }
        }

        state.error != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text("Incidents", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(12.dp))
                Button(onClick = onCreateIncident) {
                    Text("New Incident")
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Error: ${state.error}",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        state.incidents.isEmpty() -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text("Incidents", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(12.dp))
                Button(onClick = onCreateIncident) {
                    Text("New Incident")
                }
                Spacer(Modifier.height(12.dp))
                Text("No incidents found")
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text("Incidents", style = MaterialTheme.typography.headlineMedium)
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = onCreateIncident) {
                        Text("New Incident")
                    }
                }

                items(state.incidents) { incident ->
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onIncidentClick(
                                incident.id,
                                incident.title,
                                incident.severity,
                                incident.status,
                                incident.createdAt
                            )
                        }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = incident.title,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(Modifier.height(6.dp))
                            Text("Severity: ${incident.severity}")
                            Text("Status: ${incident.status}")
                            Text("Created: ${TimeUtils.formatTimestamp(incident.createdAt)}")
                        }
                    }
                }
            }
        }
    }
}