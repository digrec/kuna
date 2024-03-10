package com.digrec.kuna.feature.kunalist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digrec.kuna.core.domain.GetAllKunaUseCase
import com.digrec.kuna.core.domain.RefreshAllKunaUseCase
import com.digrec.kuna.core.domain.result.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber


/**
 * Created by Dejan Igrec
 */
class KunaListViewModel(
    val getAllKuna: GetAllKunaUseCase,
    val refreshAllKuna: RefreshAllKunaUseCase,
) : ViewModel() {

    private val _refreshState = MutableStateFlow<RefreshState>(RefreshState.NotRefreshing)
    val refreshState: StateFlow<RefreshState> = _refreshState

    private val _listUiState = MutableStateFlow<ListUiState>(ListUiState.NotLoading)
    val listUiState: StateFlow<ListUiState> = _listUiState

    init {
        load()
    }

    /**
     * Called from pull-to-refresh UI action.
     */
    fun refresh() {
        _refreshState.value = RefreshState.Refreshing

        viewModelScope.launch {
            when (val refreshResult = refreshAllKuna()) {
                is Result.Success -> {
                    _refreshState.emit(RefreshState.Success)
                }

                is Result.Error -> {
                    _refreshState.emit(RefreshState.Error(refreshResult.exception))
                }
            }

            load()
        }
    }

    fun refreshed() {
        viewModelScope.launch {
            _refreshState.emit(RefreshState.NotRefreshing)
        }
    }

    private fun load() {
        viewModelScope.launch {
            _listUiState.emit(ListUiState.Loading)

            when (val result = getAllKuna()) {
                is Result.Success -> {
                    _listUiState.emit(ListUiState.Success(result.data))
                }

                is Result.Error -> {
                    _listUiState.emit(ListUiState.Error(result.exception))
                }
            }
        }
    }

    fun removeFromCollection(kunaId: Int) {
        viewModelScope.launch {
            Timber.d("removeFromCollection: $kunaId")
        }
    }
}
