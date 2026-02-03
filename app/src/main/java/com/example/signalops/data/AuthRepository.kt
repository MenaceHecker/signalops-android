package com.example.signalops.data

import com.example.signalops.data.local.TokenStore
import com.example.signalops.data.remote.AuthApi
import com.example.signalops.data.remote.dto.LoginRequest
import com.example.signalops.data.remote.dto.RegisterRequest

class AuthRepository(
    private val api: AuthApi,
    private val tokenStore: TokenStore
) {
    suspend fun login(email: String, password: String): Result<Unit> = runCatching {
        val res = api.login(LoginRequest(email.trim().lowercase(), password))
        tokenStore.save(res.token)
    }

    suspend fun register(email: String, password: String): Result<Unit> = runCatching {
        val res = api.register(RegisterRequest(email.trim().lowercase(), password))
        tokenStore.save(res.token)
    }
}
