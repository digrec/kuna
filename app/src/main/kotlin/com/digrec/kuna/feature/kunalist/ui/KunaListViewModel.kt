package com.digrec.kuna.feature.kunalist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digrec.kuna.core.domain.GetAllKunaUseCase
import com.digrec.kuna.core.domain.model.Kuna
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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

    val listUiState: StateFlow<ListUiState> = getAllKuna()
        .map<List<Kuna>, ListUiState>(ListUiState::Success)
        .onStart { emit(ListUiState.Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ListUiState.Loading
        )

    fun removeFromCollection(kunaId: String) {
        viewModelScope.launch {
            Timber.d("removeFromCollection: $kunaId")
        }
    }
}
