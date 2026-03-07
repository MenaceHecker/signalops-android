package com.example.signalops.data

import com.example.signalops.data.remote.UserApi
import com.example.signalops.data.remote.dto.UserProfileResponse

class UserRepository(
    private val api: UserApi
) {
    suspend fun getProfile(): Result<UserProfileResponse> = runCatching {
        api.getProfile()
    }
}