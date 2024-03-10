package com.digrec.kuna.core.domain.repository

import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.result.Result


/**
 * Repository of all 25 Kuna commemorative coins.
 *
 * Created by Dejan Igrec
 */
interface KunaRepository {

    /**
     * Returns all stored 25 Kuna coins.
     */
    suspend fun getAllKuna(): Result<List<Kuna>>

    /**
     * Fetches and stores all 25 Kuna coins.
     */
    suspend fun refreshAllKuna(): Result<Unit>

    companion object {
        const val API_BASE_URL = "https://digrec.github.io"

        /**
         * Returns image URL for the given image.
         */
        fun imgUrl(image: String) = "$API_BASE_URL/kunas/res/img/$image"
    }

    sealed class ApiEndpoints(val url: String) {
        data object KunaList : ApiEndpoints("/kunas/res/json/kunas.json")
    }
}
