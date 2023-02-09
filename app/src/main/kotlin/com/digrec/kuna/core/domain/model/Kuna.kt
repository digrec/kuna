package com.digrec.kuna.core.domain.model

import java.time.LocalDate


/**
 * 25 Kuna coin entity.
 */
data class Kuna(
    val id: String,
    val title: String,
    val releaseDate: LocalDate,
    val itemsIssued: Int,
    val isCollected: Boolean = false,
)

val previewKunaList = listOf(
    Kuna(
        id = "1",
        title = "25 kuna - Croatian Danube Region",
        releaseDate = LocalDate.of(1997, 5, 28),
        itemsIssued = 300_000,
    ),
    Kuna(
        id = "2",
        title = "25 kuna - The First Croatian Esperanto Congress",
        releaseDate = LocalDate.of(1997, 6, 24),
        itemsIssued = 300_000,
    ),
    Kuna(
        id = "3",
        title = "25 kuna - Admission of the Republic of Croatia to the UNO",
        releaseDate = LocalDate.of(1997, 10, 27),
        itemsIssued = 300_000,
    ),
    Kuna(
        id = "4",
        title = "25 kuna - EXPO - Lisbon, 1998",
        releaseDate = LocalDate.of(1998, 6, 26),
        itemsIssued = 300_000,
    ),
    Kuna(
        id = "5",
        title = "25 kuna - EURO",
        releaseDate = LocalDate.of(1999, 12, 27),
        itemsIssued = 300_000,
    ),
)
