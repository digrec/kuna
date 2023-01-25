package com.digrec.kuna.core.domain.model


/**
 * 25 Kuna coin entity.
 */
data class Kuna(
    val id: String,
    val title: String,
    val description: String,
    val isCollected: Boolean = false,
)

val previewKunaList = listOf(
    Kuna(
        id = "1",
        title = "25 kuna - Croatian Danube Region",
        description = "28 May 1997",
    ),
    Kuna(
        id = "2",
        title = "25 kuna - The First Croatian Esperanto Congress",
        description = "24 June 1997",
    ),
    Kuna(
        id = "3",
        title = "25 kuna - Admission of the Republic of Croatia to the UNO",
        description = "27 October 1997",
    ),
    Kuna(
        id = "4",
        title = "25 kuna - EXPO - Lisbon, 1998",
        description = "26 June 1998",
    ),
    Kuna(
        id = "5",
        title = "25 kuna - EURO",
        description = "29 December 1999",
    ),
)
