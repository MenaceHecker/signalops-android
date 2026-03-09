package com.example.signalops.ui.dashboard

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

data class DashboardUiState(
    val loading: Boolean = false,
    val incidents: List<IncidentResponse> = emptyList(),
    val error: String? = null
)

class DashboardViewModel(app: Application) : AndroidViewModel(app) {

    private val tokenStore = TokenStore(app.applicationContext)

    private val repo = IncidentRepository(
        api = ApiClient.createIncidentApi(tokenStore)
    )

    private val _state = MutableStateFlow(DashboardUiState())
    val state: StateFlow<DashboardUiState> = _state.asStateFlow()

    fun loadSummary() {
        viewModelScope.launch {
            _state.value = DashboardUiState(loading = true)

            repo.getIncidents()
                .onSuccess { incidents ->
                    _state.value = DashboardUiState(incidents = incidents)
                }
                .onFailure { e ->
                    _state.value = DashboardUiState(
                        error = e.message ?: "Failed to load dashboard summary"
                    )
                }
        }
    }
}