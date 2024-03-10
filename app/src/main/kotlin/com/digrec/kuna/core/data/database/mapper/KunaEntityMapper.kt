package com.digrec.kuna.core.data.database.mapper

import com.digrec.kuna.core.data.database.model.KunaEntity
import com.digrec.kuna.core.domain.model.Kuna


fun KunaEntity.toKuna() = Kuna(
    id = id,
    title = title,
    releaseDate = releaseDate,
    itemsIssued = itemsIssued,
    imageObverse = imageObverse,
    imageReverse = imageReverse,
)

fun Kuna.toKunaEntity() = KunaEntity(
    id = id,
    title = title,
    releaseDate = releaseDate,
    itemsIssued = itemsIssued,
    imageObverse = imageObverse,
    imageReverse = imageReverse,
)
