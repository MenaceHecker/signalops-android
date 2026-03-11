package com.example.signalops.ui.incidents

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalops.data.IncidentRepository
import com.example.signalops.data.local.TokenStore
import com.example.signalops.data.remote.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class IncidentDetailUiState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)

class IncidentDetailViewModel(app: Application) : AndroidViewModel(app) {

    private val tokenStore = TokenStore(app.applicationContext)

    private val repo = IncidentRepository(
        api = ApiClient.createIncidentApi(tokenStore)
    )

    private val _state = MutableStateFlow(IncidentDetailUiState())
    val state: StateFlow<IncidentDetailUiState> = _state.asStateFlow()

    fun updateStatus(id: Long, status: String) {
        viewModelScope.launch {
            _state.value = IncidentDetailUiState(loading = true)

            repo.updateIncidentStatus(id, status)
                .onSuccess {
                    _state.value = IncidentDetailUiState(success = true)
                }
                .onFailure { e ->
                    _state.value = IncidentDetailUiState(
                        error = e.message ?: "Failed to update incident"
                    )
                }
        }
    }
}