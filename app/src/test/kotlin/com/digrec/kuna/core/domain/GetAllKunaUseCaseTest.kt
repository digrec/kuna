package com.digrec.kuna.core.domain

import com.digrec.kuna.core.domain.result.Result
import com.digrec.kuna.core.testing.data.KunaTestData
import com.digrec.kuna.core.testing.repository.TestKunaRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/** Unit tests for [GetAllKunaUseCase]. */
class GetAllKunaUseCaseTest {

    private val repository = TestKunaRepository()
    private val useCase = GetAllKunaUseCase(repository)

    @Test
    fun `when invoke then it should return result from repository`() = runTest {
        repository.sendKunas(KunaTestData.kunaList)

        val actualResult = useCase()

        assert(actualResult is Result.Success)
        assertEquals(KunaTestData.kunaList, (actualResult as Result.Success).data)
    }

    @Test
    fun `given error from repository when invoke then it should return error result`() = runTest {
        // We still need to mock for error scenarios as TestKunaRepository is currently hardcoded
        // for success
        val mockRepository = mockk<com.digrec.kuna.core.domain.repository.KunaRepository>()
        val errorUseCase = GetAllKunaUseCase(mockRepository)
        val exception = Exception("test error")
        val expectedResult = Result.Error(exception)
        coEvery { mockRepository.getAllKuna() } returns expectedResult

        val actualResult = errorUseCase()

        assertEquals(expectedResult, actualResult)
    }
}
