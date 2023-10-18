package com.digrec.kuna.feature.kunalist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digrec.kuna.core.domain.GetAllKunaUseCase
import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber


/**
 * Created by Dejan Igrec
 */
class KunaListViewModel(
    getAllKuna: GetAllKunaUseCase,
) : ViewModel() {

    val listUiState: StateFlow<ListUiState> = getAllKuna().toState()

    fun removeFromCollection(kunaId: Int) {
        viewModelScope.launch {
            Timber.d("removeFromCollection: $kunaId")
        }
    }

    private fun Flow<Result<List<Kuna>>>.toState(): StateFlow<ListUiState> =
        this
            .map {
                when (it) {
                    is Result.Success -> ListUiState.Success(it.data)
                    is Result.Error -> ListUiState.Error(it.exception)
                }
            }.onStart { emit(ListUiState.Loading) }
            .catch { emit(ListUiState.Error(it)) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ListUiState.Loading
            )
}
