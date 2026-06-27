package com.digrec.kuna.feature.kunalist.ui.component

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import com.digrec.kuna.core.domain.model.Kuna
import timber.log.Timber

/**
 * An extension on [LazyGridScope] defining a list of Kuna coins.
 *
 * Created by Dejan Igrec
 */
fun LazyGridScope.kunaList(list: List<Kuna>, onCoinCheckedChanged: (Int, Boolean) -> Unit) {
    items(list, key = { it.id }) { kuna ->
        KunaCard(
            kuna = kuna,
            isChecked = kuna.isCollected,
            onClick = { Timber.d("onClick: ${kuna.id}") },
            onToggleCheckmark = { onCoinCheckedChanged(kuna.id, !kuna.isCollected) },
        )
    }
}
