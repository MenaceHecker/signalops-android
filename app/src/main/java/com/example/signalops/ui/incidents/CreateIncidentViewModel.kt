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

data class CreateIncidentUiState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)

class CreateIncidentViewModel(app: Application) : AndroidViewModel(app) {

    private val tokenStore = TokenStore(app.applicationContext)

    private val repo = IncidentRepository(
        api = ApiClient.createIncidentApi(tokenStore)
    )

    private val _state = MutableStateFlow(CreateIncidentUiState())
    val state: StateFlow<CreateIncidentUiState> = _state.asStateFlow()

    fun createIncident(
        title: String,
        severity: String,
        status: String
    ) {
        viewModelScope.launch {
            _state.value = CreateIncidentUiState(loading = true)

            repo.createIncident(title, severity, status)
                .onSuccess {
                    _state.value = CreateIncidentUiState(success = true)
                }
                .onFailure { e ->
                    _state.value = CreateIncidentUiState(
                        error = e.message ?: "Failed to create incident"
                    )
                }
        }
    }
}