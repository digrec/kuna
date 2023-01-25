package com.digrec.kuna.core.ui.kunaList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digrec.kuna.core.domain.GetAllKunaUseCase
import com.digrec.kuna.core.domain.model.Kuna
import kotlinx.coroutines.flow.*
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
