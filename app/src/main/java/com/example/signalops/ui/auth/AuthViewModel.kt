package com.example.signalops.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalops.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val loading: Boolean = false,
    val error: String? = null
)

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {
    private val _state = MutableStateFlow(AuthUiState())
    val state: StateFlow<AuthUiState> = _state

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        _state.value = AuthUiState(loading = true)
        viewModelScope.launch {
            val result = repo.login(email, password)
            _state.value = AuthUiState(
                loading = false,
                error = result.exceptionOrNull()?.message
            )
            if (result.isSuccess) onSuccess()
        }
    }

    fun signup(email: String, password: String, onSuccess: () -> Unit) {
        _state.value = AuthUiState(loading = true)
        viewModelScope.launch {
            val result = repo.register(email, password)
            _state.value = AuthUiState(
                loading = false,
                error = result.exceptionOrNull()?.message
            )
            if (result.isSuccess) onSuccess()
        }
    }
}
