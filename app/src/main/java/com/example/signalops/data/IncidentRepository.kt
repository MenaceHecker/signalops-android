package com.example.signalops.data

import com.example.signalops.data.remote.IncidentApi
import com.example.signalops.data.remote.dto.CreateIncidentRequest
import com.example.signalops.data.remote.dto.IncidentResponse

class IncidentRepository(
    private val api: IncidentApi
) {
    suspend fun getIncidents(): Result<List<IncidentResponse>> = runCatching {
        api.getIncidents()
    }

    suspend fun createIncident(
        title: String,
        severity: String,
        status: String
    ): Result<IncidentResponse> = runCatching {
        api.createIncident(
            CreateIncidentRequest(
                title = title,
                severity = severity,
                status = status
            )
        )
    }
}