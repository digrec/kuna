package com.digrec.kuna.core.data.repository

import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.model.previewKunaList
import com.digrec.kuna.core.domain.repository.KunaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext


/**
 *
 */
class KunaRepositoryImpl(
    private val ioDispatcher: CoroutineContext,
) : KunaRepository {

    override fun getAllKuna(): Flow<List<Kuna>> =
        flow {
            emit(previewKunaList)
        }.flowOn(ioDispatcher)
}
