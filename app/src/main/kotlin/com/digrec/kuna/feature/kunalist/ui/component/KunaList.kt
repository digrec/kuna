package com.digrec.kuna.feature.kunalist.ui.component

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import com.digrec.kuna.feature.kunalist.ui.ListUiState
import timber.log.Timber


/**
 * An extension on [LazyGridScope] defining a list of Kuna coins.
 * Depending on the [listState], this might emit no items.
 *
 * Created by Dejan Igrec
 */
fun LazyGridScope.kunaList(
    listState: ListUiState,
    onCoinCheckedChanged: (Int, Boolean) -> Unit
) {
    when (listState) {
        is ListUiState.Loading -> Unit
        is ListUiState.Success -> {
            items(listState.list, key = { it.id }) { kuna ->
                KunaCard(
                    kuna = kuna,
                    isChecked = kuna.isCollected,
                    onClick = { Timber.d("onClick: ${kuna.id}") },
                    onToggleCheckmark = { onCoinCheckedChanged(kuna.id, !kuna.isCollected) }
                )
            }
        }

        is ListUiState.Error -> Unit
    }
}
