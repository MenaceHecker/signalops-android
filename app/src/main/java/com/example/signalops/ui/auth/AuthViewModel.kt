package com.example.signalops.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalops.data.AuthRepository
import com.example.signalops.data.local.TokenStore
import com.example.signalops.data.remote.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = AuthRepository(
        api = ApiClient.authApi,
        tokenStore = TokenStore(app.applicationContext)
    )

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _state.value = AuthState(loading = true)

            repo.login(email, password)
                .onSuccess {
                    _state.value = AuthState()
                    onSuccess()
                }
                .onFailure { e ->
                    _state.value = AuthState(error = e.message ?: "Login failed")
                }
        }
    }

    fun signup(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _state.value = AuthState(loading = true)

            repo.register(email, password)
                .onSuccess {
                    _state.value = AuthState()
                    onSuccess()
                }
                .onFailure { e ->
                    _state.value = AuthState(error = e.message ?: "Signup failed")
                }
        }
    }
}
