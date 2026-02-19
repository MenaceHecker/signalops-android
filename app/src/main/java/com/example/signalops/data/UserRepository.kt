package com.example.signalops.data

import com.example.signalops.data.remote.UserApi
import com.example.signalops.data.remote.dto.UserProfileResponse

class UserRepository(private val api: UserApi) {
    suspend fun me(): Result<UserProfileResponse> = runCatching { api.me() }
}