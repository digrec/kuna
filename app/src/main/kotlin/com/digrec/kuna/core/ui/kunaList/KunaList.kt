package com.digrec.kuna.core.ui.kunaList

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import com.digrec.kuna.ui.KunaCard
import timber.log.Timber


/**
 * An extension on [LazyGridScope] defining a list of Kuna coins.
 * Depending on the [listState], this might emit no items.
 *
 * Created by Dejan Igrec
 */
fun LazyGridScope.kunaList(
    listState: ListUiState,
    onCoinCheckedChanged: (String, Boolean) -> Unit
) {
    when (listState) {
        ListUiState.Loading -> Unit
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
    }
}
