package com.example.signalops.data.remote

import com.example.signalops.data.remote.dto.UserProfileResponse
import retrofit2.http.GET

interface UserApi {
    @GET("/api/me")
    suspend fun me(): UserProfileResponse
}