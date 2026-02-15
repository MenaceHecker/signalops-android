package com.example.signalops.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardScreen(
    userName: String = "Tushar",
) {
    var isRefreshing by remember { mutableStateOf(false) }
    var env by remember { mutableStateOf("Local") }

    var stats by remember { mutableStateOf(fakeStats()) }
    var incidents by remember { mutableStateOf(fakeIncidents()) }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
        }
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                GreetingHeader(
                    userName = userName,
                    env = env,
                    onToggleEnv = { env = if (env == "Local") "Prod" else "Local" }
                )
            }

            item {
                StatsRow(stats = stats)
            }

            item {
                Text(
                    text = "Recent Incidents",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            items(incidents) { inc ->
                IncidentCard(inc)
            }

            item {
                Spacer(Modifier.height(24.dp))
                Text(
                    text = "Pull down to refresh • fake data for now",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp)
        )
    }
}
@Composable
private fun GreetingHeader(
    userName: String,
    env: String,
    onToggleEnv: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hey, $userName",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Here’s what’s happening right now",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        AssistChip(
            onClick = onToggleEnv,
            label = { Text(env) }
        )
    }
}
@Composable
private fun StatsRow(stats: DashboardStats) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(
            title = "Services",
            value = "${stats.servicesUp}/${stats.servicesTotal}",
            subtitle = "Healthy"
        )
        StatCard(
            title = "Incidents",
            value = "${stats.incidentsToday}",
            subtitle = "Today"
        )
        StatCard(
            title = "Latency",
            value = "${stats.p95LatencyMs}ms",
            subtitle = "p95"
        )
    }
}
@Composable
private fun StatCard(
    title: String,
    value: String,
    subtitle: String
) {
    ElevatedCard(
        modifier = Modifier.weight(1f)
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
@Composable
private fun IncidentCard(inc: IncidentPreview) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = inc.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(10.dp))
                SeverityPill(severity = inc.severity)
            }

            Spacer(Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = inc.service,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = inc.timeAgo,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun SeverityPill(severity: String) {
    val label = severity.uppercase()

    Surface(
        shape = MaterialTheme.shapes.large,
        tonalElevation = 1.dp
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
        )
    }
}

