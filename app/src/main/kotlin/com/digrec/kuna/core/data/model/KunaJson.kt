package com.digrec.kuna.core.data.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable


/**
 * 25 Kuna coin data model.
 */
@Serializable
data class KunaJson(
    val id: String,
    val title: String,
    val releaseDate: LocalDate,
    val itemsIssued: Int,
)