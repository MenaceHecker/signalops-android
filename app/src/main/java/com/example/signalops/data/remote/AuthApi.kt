package com.example.signalops.data.remote

import com.example.signalops.data.remote.dto.AuthResponse
import com.example.signalops.data.remote.dto.LoginRequest
import com.example.signalops.data.remote.dto.RegisterRequest
import com.example.signalops.data.remote.dto.UserProfileResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET


data class MeResponse(
    val email: String,
    val authenticated: Boolean
)
interface AuthApi {
    @POST("api/auth/register")
    suspend fun register(@Body req: RegisterRequest): AuthResponse

    @POST("api/auth/login")
    suspend fun login(@Body req: LoginRequest): AuthResponse

    @GET("/api/me")
    suspend fun me(): UserProfileResponse

}
