package com.example.signalops.data.remote

import com.example.signalops.data.local.TokenStore
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    // ✅ IMPORTANT: Retrofit baseUrl must end with "/"
    private const val BASE_URL = "http://10.0.2.2:8080/"

    fun createAuthApi(tokenStore: TokenStore): AuthApi =
        retrofit(tokenStore).create(AuthApi::class.java)

    fun createUserApi(tokenStore: TokenStore): UserApi =
        retrofit(tokenStore).create(UserApi::class.java)

    private fun retrofit(tokenStore: TokenStore): Retrofit {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttp = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenStore))
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}