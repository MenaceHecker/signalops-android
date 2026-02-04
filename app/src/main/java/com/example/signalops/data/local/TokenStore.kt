package com.example.signalops.data.local

import android.content.Context
import androidx.core.content.edit

class TokenStore(context: Context) {

    private val prefs = context.getSharedPreferences("signalops", Context.MODE_PRIVATE)

    fun save(token: String) {
        prefs.edit { putString("jwt", token) }
        android.util.Log.d("TokenStore", "saved token length=${token.length}")
    }

    fun get(): String? = prefs.getString("jwt", null).also {
        android.util.Log.d("TokenStore", "loaded token length=${it?.length ?: 0}")
    }


    fun clear() {
        prefs.edit { remove("jwt") }
    }
}
