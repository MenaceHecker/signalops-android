package com.example.signalops.data.remote.dto

data class IncidentResponse(
    val id: Long,
    val title: String,
    val severity: String,
    val status: String,
    val createdAt: String
)