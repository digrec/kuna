package com.digrec.kuna.core.testing.repository

import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.repository.KunaRepository
import com.digrec.kuna.core.domain.result.Result
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * A fake [KunaRepository] for unit testing.
 *
 * Created by Dejan Igrec
 */
class TestKunaRepository : KunaRepository {

    /** A flow that emits the current list of [Kuna] objects. */
    private val kunasFlow =
        MutableSharedFlow<List<Kuna>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override suspend fun getAllKuna(): Result<List<Kuna>> {
        return Result.Success(kunasFlow.replayCache.firstOrNull() ?: emptyList())
    }

    override suspend fun refreshAllKuna(): Result<Unit> {
        return Result.Success(Unit)
    }

    /** A test-only API to send a list of [Kuna] objects to the repository. */
    fun sendKunas(kunas: List<Kuna>) {
        kunasFlow.tryEmit(kunas)
    }
}
