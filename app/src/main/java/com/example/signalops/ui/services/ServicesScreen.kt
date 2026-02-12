package com.example.signalops.ui.services

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ServicesScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("Services", style = MaterialTheme.typography.headlineMedium)
        Text("We’ll list monitored services here.", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
