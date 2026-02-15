package com.example.signalops.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random

private data class DashboardStats(
    val servicesUp: Int,
    val servicesTotal: Int,
    val incidentsToday: Int,
    val p95LatencyMs: Int
)

private data class IncidentPreview(
    val title: String,
    val service: String,
    val severity: String,
    val timeAgo: String
)

private fun fakeStats(): DashboardStats {
    val total = 12
    val up = Random.nextInt(10, 13)
    return DashboardStats(
        servicesUp = up.coerceAtMost(total),
        servicesTotal = total,
        incidentsToday = Random.nextInt(0, 4),
        p95LatencyMs = Random.nextInt(80, 420)
    )
}
private fun fakeIncidents(): List<IncidentPreview> {
    val all = listOf(
        IncidentPreview("High error rate detected", "auth-api", "P1", "5m ago"),
        IncidentPreview("Latency spike", "metrics-ingest", "P2", "18m ago"),
        IncidentPreview("DB connection pool saturation", "postgres", "P2", "41m ago"),
        IncidentPreview("Elevated 5xx responses", "gateway", "P1", "1h ago"),
        IncidentPreview("Slow queries", "reporting", "P3", "2h ago"),
        IncidentPreview("CPU throttling", "worker", "P3", "3h ago"),
    )
    val count = Random.nextInt(1, 5)
    return all.shuffled().take(count)
}