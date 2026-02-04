package com.example.signalops.data.remote

import com.example.signalops.data.local.TokenStore
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenStore: TokenStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenStore.get()
        val req = chain.request()

        val authedReq =
            if (!token.isNullOrBlank()) {
                req.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else req

        return chain.proceed(authedReq)
    }
}
