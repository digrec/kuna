package com.digrec.kuna.core.domain

import app.cash.turbine.test
import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.result.Result
import com.digrec.kuna.core.testing.data.KunaTestData
import com.digrec.kuna.core.testing.repository.TestKunaRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/** Unit tests for [GetAllKunaUseCase]. */
class GetAllKunaUseCaseTest {

    private val repository = TestKunaRepository()
    private val useCase = GetAllKunaUseCase(repository)

    @Test
    fun `when invoke then it should return flow from repository`() = runTest {
        useCase().test {
            assertEquals(Result.Success(emptyList<Kuna>()), awaitItem())

            repository.sendKunas(KunaTestData.kunaList)
            assertEquals(Result.Success(KunaTestData.kunaList), awaitItem())
        }
    }
}
