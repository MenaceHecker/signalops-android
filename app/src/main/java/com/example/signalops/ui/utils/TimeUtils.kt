package com.example.signalops.ui.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object TimeUtils {

    private val formatter = DateTimeFormatter
        .ofPattern("MMM d, h:mm a")
        .withZone(ZoneId.systemDefault())

    fun formatTimestamp(timestamp: String): String {
        return try {
            val instant = Instant.parse(timestamp)
            formatter.format(instant)
        } catch (e: Exception) {
            timestamp
        }
    }
}