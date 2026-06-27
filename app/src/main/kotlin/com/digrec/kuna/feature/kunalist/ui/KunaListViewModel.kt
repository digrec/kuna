package com.digrec.kuna.feature.kunalist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digrec.kuna.core.domain.GetAllKunaUseCase
import com.digrec.kuna.core.domain.RefreshAllKunaUseCase
import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.result.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

sealed interface KunaListUiState {
    data object Loading : KunaListUiState

    data class Success(
        val list: List<Kuna>,
        val isRefreshing: Boolean = false,
        val refreshError: Throwable? = null,
    ) : KunaListUiState

    data class Error(val exception: Throwable? = null) : KunaListUiState
}

/** Created by Dejan Igrec */
class KunaListViewModel(
    private val getAllKuna: GetAllKunaUseCase,
    private val refreshAllKuna: RefreshAllKunaUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<KunaListUiState>(KunaListUiState.Loading)
    val uiState: StateFlow<KunaListUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    /** Called from pull-to-refresh UI action. */
    fun refresh() {
        val currentState = _uiState.value
        if (currentState !is KunaListUiState.Success) return

        _uiState.update { currentState.copy(isRefreshing = true, refreshError = null) }

        viewModelScope.launch {
            val result = refreshAllKuna()
            if (result is Result.Error) {
                _uiState.update { state ->
                    if (state is KunaListUiState.Success) {
                        state.copy(isRefreshing = false, refreshError = result.exception)
                    } else {
                        state
                    }
                }
            } else {
                load()
            }
        }
    }

    /** Resets the refresh error state. */
    fun clearRefreshError() {
        _uiState.update { state ->
            if (state is KunaListUiState.Success) state.copy(refreshError = null) else state
        }
    }

    private fun load() {
        viewModelScope.launch {
            if (_uiState.value !is KunaListUiState.Success) {
                _uiState.emit(KunaListUiState.Loading)
            }

            when (val result = getAllKuna()) {
                is Result.Success -> {
                    _uiState.emit(KunaListUiState.Success(list = result.data))
                }

                is Result.Error -> {
                    _uiState.emit(KunaListUiState.Error(result.exception))
                }
            }
        }
    }

    fun removeFromCollection(kunaId: Int) {
        viewModelScope.launch { Timber.d("removeFromCollection: $kunaId") }
    }
}
