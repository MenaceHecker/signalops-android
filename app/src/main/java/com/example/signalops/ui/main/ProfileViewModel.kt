package com.example.signalops.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalops.data.UserRepository
import com.example.signalops.data.local.TokenStore
import com.example.signalops.data.remote.ApiClient
import com.example.signalops.data.remote.dto.UserProfileResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(
    val loading: Boolean = false,
    val profile: UserProfileResponse? = null,
    val error: String? = null
)

class ProfileViewModel(app: Application) : AndroidViewModel(app) {

    private val tokenStore = TokenStore(app.applicationContext)

    private val repo = UserRepository(
        api = ApiClient.createUserApi(tokenStore)
    )

    private val _state = MutableStateFlow(ProfileUiState())
    val state: StateFlow<ProfileUiState> = _state.asStateFlow()

    fun loadProfile() {
        viewModelScope.launch {
            _state.value = ProfileUiState(loading = true)

            repo.getProfile()
                .onSuccess { profile ->
                    _state.value = ProfileUiState(profile = profile)
                }
                .onFailure { e ->
                    _state.value = ProfileUiState(
                        error = e.message ?: "Failed to load profile"
                    )
                }
        }
    }
}