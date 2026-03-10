package com.example.signalops.data.remote

import com.example.signalops.data.remote.dto.CreateIncidentRequest
import com.example.signalops.data.remote.dto.IncidentResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IncidentApi {
    @GET("/api/incidents")
    suspend fun getIncidents(): List<IncidentResponse>

    @POST("/api/incidents")
    suspend fun createIncident(@Body req: CreateIncidentRequest): IncidentResponse
}