package com.example.signalops.data.remote.dto

data class CreateIncidentRequest(
    val title: String,
    val severity: String,
    val status: String
)