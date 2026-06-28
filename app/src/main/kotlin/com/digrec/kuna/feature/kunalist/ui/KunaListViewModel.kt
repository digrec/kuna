package com.digrec.kuna.feature.kunalist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digrec.kuna.core.domain.GetAllKunaUseCase
import com.digrec.kuna.core.domain.RefreshAllKunaUseCase
import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.result.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
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
    getAllKuna: GetAllKunaUseCase,
    private val refreshAllKuna: RefreshAllKunaUseCase,
) : ViewModel() {

    private val isRefreshing = MutableStateFlow(false)
    private val refreshError = MutableStateFlow<Throwable?>(null)

    val uiState: StateFlow<KunaListUiState> =
        combine(getAllKuna(), isRefreshing, refreshError) { result, refreshing, error ->
                when (result) {
                    is Result.Success -> {
                        KunaListUiState.Success(
                            list = result.data,
                            isRefreshing = refreshing,
                            refreshError = error,
                        )
                    }

                    is Result.Error -> {
                        KunaListUiState.Error(exception = result.exception)
                    }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = KunaListUiState.Loading,
            )

    init {
        refresh()
    }

    /** Called from pull-to-refresh UI action. */
    fun refresh() {
        if (isRefreshing.value) return

        isRefreshing.value = true
        refreshError.value = null

        viewModelScope.launch {
            val result = refreshAllKuna()
            if (result is Result.Error) {
                refreshError.value = result.exception
            }
            isRefreshing.value = false
        }
    }

    /** Resets the refresh error state. */
    fun clearRefreshError() {
        refreshError.value = null
    }

    fun removeFromCollection(kunaId: Int) {
        viewModelScope.launch { Timber.d("removeFromCollection: $kunaId") }
    }
}
