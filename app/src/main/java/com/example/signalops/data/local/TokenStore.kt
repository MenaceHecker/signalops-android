package com.example.signalops.data.local

import android.content.Context
import androidx.core.content.edit

class TokenStore(context: Context) {

    private val prefs = context.getSharedPreferences("signalops", Context.MODE_PRIVATE)

    fun save(token: String) {
        prefs.edit { putString("jwt", token) }
    }

    fun get(): String? = prefs.getString("jwt", null)

    fun clear() {
        prefs.edit { remove("jwt") }
    }
}
