package com.digrec.kuna.core.domain.repository

import com.digrec.kuna.core.domain.model.Kuna
import kotlinx.coroutines.flow.Flow


/**
 * Repository of all 25 Kuna commemorative coins.
 *
 * Created by Dejan Igrec
 */
interface KunaRepository {

    /**
     * Returns all 25 Kuna coins as a stream.
     */
    fun getAllKuna(): Flow<List<Kuna>>
}
