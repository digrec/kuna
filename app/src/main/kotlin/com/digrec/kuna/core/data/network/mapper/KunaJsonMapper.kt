package com.digrec.kuna.core.data.network.mapper

import com.digrec.kuna.core.data.network.model.KunaJson
import com.digrec.kuna.core.domain.model.Kuna


fun KunaJson.toKuna() = Kuna(
    id = id,
    title = title,
    releaseDate = releaseDate,
    itemsIssued = itemsIssued,
    imageObverse = imageObverse,
    imageReverse = imageReverse,
)
