package com.example.signalops.ui.incidents

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.signalops.data.IncidentRepository
import com.example.signalops.data.local.TokenStore
import com.example.signalops.data.remote.ApiClient
import com.example.signalops.data.remote.dto.IncidentResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class IncidentUiState(
    val loading: Boolean = false,
    val incidents: List<IncidentResponse> = emptyList(),
    val error: String? = null
)

class IncidentViewModel(app: Application) : AndroidViewModel(app) {

    private val tokenStore = TokenStore(app.applicationContext)

    private val repo = IncidentRepository(
        api = ApiClient.createIncidentApi(tokenStore)
    )

    private val _state = MutableStateFlow(IncidentUiState())
    val state: StateFlow<IncidentUiState> = _state.asStateFlow()

    fun loadIncidents() {
        viewModelScope.launch {
            _state.value = IncidentUiState(loading = true)

            repo.getIncidents()
                .onSuccess { incidents ->
                    _state.value = IncidentUiState(incidents = incidents)
                }
                .onFailure { e ->
                    _state.value = IncidentUiState(
                        error = e.message ?: "Failed to load incidents"
                    )
                }
        }
    }
}