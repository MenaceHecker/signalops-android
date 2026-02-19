package com.example.signalops.data.remote.dto

data class UserProfileResponse(
    val id: Long,
    val email: String,
    val role: String,
    val createdAt: String
)
