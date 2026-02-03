package com.example.signalops.data.remote

import com.example.signalops.data.remote.dto.AuthResponse
import com.example.signalops.data.remote.dto.LoginRequest
import com.example.signalops.data.remote.dto.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/auth/register")
    suspend fun register(@Body req: RegisterRequest): AuthResponse

    @POST("/api/auth/login")
    suspend fun login(@Body req: LoginRequest): AuthResponse
}
