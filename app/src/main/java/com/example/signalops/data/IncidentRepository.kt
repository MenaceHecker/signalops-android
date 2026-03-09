package com.example.signalops.data

import com.example.signalops.data.remote.IncidentApi
import com.example.signalops.data.remote.dto.IncidentResponse

class IncidentRepository(
    private val api: IncidentApi
) {
    suspend fun getIncidents(): Result<List<IncidentResponse>> = runCatching {
        api.getIncidents()
    }
}