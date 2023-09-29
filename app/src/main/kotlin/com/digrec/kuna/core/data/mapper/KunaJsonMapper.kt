package com.digrec.kuna.core.data.mapper

import com.digrec.kuna.core.data.model.KunaJson
import com.digrec.kuna.core.domain.model.Kuna


fun KunaJson.toKuna() = Kuna(
    id = id,
    title = title,
    releaseDate = releaseDate,
    itemsIssued = itemsIssued,
)
