package com.example.signalops.data.remote

import com.example.signalops.data.local.TokenStore
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenStore: TokenStore
) : okhttp3.Interceptor {

    override fun intercept(chain: okhttp3.Interceptor.Chain): okhttp3.Response {
        val req = chain.request()
        val path = req.url.encodedPath
        val isAuth = path.startsWith("/api/auth/")
        val token = tokenStore.get()

        val newReq = if (!isAuth && !token.isNullOrBlank()) {
            req.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else req

        return chain.proceed(newReq)
    }
}
