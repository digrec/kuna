package com.digrec.kuna.core.domain.model

import kotlinx.datetime.LocalDate

/** 25 Kuna coin entity. */
data class Kuna(
    val id: Int,
    val title: String,
    val releaseDate: LocalDate,
    val itemsIssued: Int,
    val imageObverse: String,
    val imageReverse: String,
    val isCollected: Boolean = false,
)
