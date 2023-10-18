package com.digrec.kuna.core.data.network.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable


/**
 * 25 Kuna coin data model.
 */
@Serializable
data class KunaJson(
    val id: Int,
    val title: String,
    val releaseDate: LocalDate,
    val itemsIssued: Int,
    val imageObverse: String,
    val imageReverse: String,
)
