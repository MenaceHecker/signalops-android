package com.example.signalops.data.remote

import com.example.signalops.data.remote.dto.CreateIncidentRequest
import com.example.signalops.data.remote.dto.IncidentResponse
import com.example.signalops.data.remote.dto.UpdateIncidentStatusRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface IncidentApi {
    @GET("/api/incidents")
    suspend fun getIncidents(): List<IncidentResponse>

    @POST("/api/incidents")
    suspend fun createIncident(@Body req: CreateIncidentRequest): IncidentResponse

    @PATCH("/api/incidents/{id}/status")
    suspend fun updateIncidentStatus(
        @Path("id") id: Long,
        @Body req: UpdateIncidentStatusRequest
    ): IncidentResponse
}