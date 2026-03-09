package com.example.signalops.data.remote

import com.example.signalops.data.remote.dto.IncidentResponse
import retrofit2.http.GET

interface IncidentApi {
    @GET("/api/incidents")
    suspend fun getIncidents(): List<IncidentResponse>
}