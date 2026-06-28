package com.digrec.kuna.core.testing.repository

import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.repository.KunaRepository
import com.digrec.kuna.core.domain.result.Result
import com.digrec.kuna.core.domain.result.toResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * A fake [KunaRepository] for unit testing.
 *
 * Created by Dejan Igrec
 */
class TestKunaRepository : KunaRepository {

    /** A flow that emits the current list of [Kuna] objects. */
    private val kunasFlow = MutableStateFlow<List<Kuna>>(emptyList())

    override fun getAllKuna(): Flow<Result<List<Kuna>>> {
        return kunasFlow.asStateFlow().toResult()
    }

    override suspend fun refreshAllKuna(): Result<Unit> {
        return Result.Success(Unit)
    }

    /** A test-only API to send a list of [Kuna] objects to the repository. */
    fun sendKunas(kunas: List<Kuna>) {
        kunasFlow.value = kunas
    }
}
