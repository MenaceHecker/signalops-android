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

data class ProfileState(
    val loading: Boolean = false,
    val profile: UserProfileResponse? = null,
    val error: String? = null
)

class ProfileViewModel(app: Application) : AndroidViewModel(app) {
    private val tokenStore = TokenStore(app.applicationContext)
    private val repo = UserRepository(ApiClient.createUserApi(tokenStore))

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    fun fetchProfile() {
        viewModelScope.launch {
            _state.value = ProfileState(loading = true)
            repo.me()
                .onSuccess { _state.value = ProfileState(profile = it) }
                .onFailure { _state.value = ProfileState(error = it.message ?: "Failed") }
        }
    }
}