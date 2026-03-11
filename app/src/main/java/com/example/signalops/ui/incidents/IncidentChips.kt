package com.example.signalops.ui.incidents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SeverityChip(severity: String) {
    val bg = when (severity.uppercase()) {
        "HIGH" -> Color(0xFFFFE5E5)
        "MEDIUM" -> Color(0xFFFFF1E0)
        "LOW" -> Color(0xFFFFF8D6)
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    val fg = when (severity.uppercase()) {
        "HIGH" -> Color(0xFFB42318)
        "MEDIUM" -> Color(0xFFB54708)
        "LOW" -> Color(0xFF8A6D1F)
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Box(
        modifier = Modifier
            .background(bg, RoundedCornerShape(999.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = severity.uppercase(),
            color = fg,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun StatusChip(status: String) {
    val bg = when (status.uppercase()) {
        "OPEN" -> Color(0xFFE8F1FF)
        "INVESTIGATING" -> Color(0xFFFFEAEA)
        "MONITORING" -> Color(0xFFFFF6E5)
        "RESOLVED" -> Color(0xFFE7F8EC)
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    val fg = when (status.uppercase()) {
        "OPEN" -> Color(0xFF175CD3)
        "INVESTIGATING" -> Color(0xFFB42318)
        "MONITORING" -> Color(0xFFB54708)
        "RESOLVED" -> Color(0xFF067647)
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Box(
        modifier = Modifier
            .background(bg, RoundedCornerShape(999.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = status.uppercase(),
            color = fg,
            style = MaterialTheme.typography.labelMedium
        )
    }
}