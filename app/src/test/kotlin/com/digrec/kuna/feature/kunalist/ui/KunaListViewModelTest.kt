package com.digrec.kuna.feature.kunalist.ui

import app.cash.turbine.test
import com.digrec.kuna.core.domain.GetAllKunaUseCase
import com.digrec.kuna.core.domain.RefreshAllKunaUseCase
import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.result.Result
import com.digrec.kuna.core.testing.MainDispatcherRule
import com.digrec.kuna.core.testing.data.KunaTestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/** Unit tests for [KunaListViewModel]. */
@OptIn(ExperimentalCoroutinesApi::class)
class KunaListViewModelTest {

    @get:Rule val mainDispatcherRule = MainDispatcherRule()

    private val getAllKuna: GetAllKunaUseCase = mockk()
    private val refreshAllKuna: RefreshAllKunaUseCase = mockk()

    private lateinit var viewModel: KunaListViewModel

    @Test
    fun `when init then it should load kuna list and trigger refresh`() = runTest {
        every { getAllKuna() } returns MutableStateFlow(Result.Success(KunaTestData.kunaList))
        coEvery { refreshAllKuna() } returns Result.Success(Unit)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.uiState.test {
            // Loading (initial), then Success from combine, then Success from init refresh
            val successState = expectMostRecentItem()
            assert(successState is KunaListUiState.Success)
            assertEquals(KunaTestData.kunaList, (successState as KunaListUiState.Success).list)
        }

        coVerify(exactly = 1) { getAllKuna() }
        coVerify(exactly = 1) { refreshAllKuna() }
    }

    @Test
    fun `when refresh then it should update states correctly`() = runTest {
        every { getAllKuna() } returns MutableStateFlow(Result.Success(KunaTestData.kunaList))
        // Init refresh finishes quickly
        coEvery { refreshAllKuna() } returns Result.Success(Unit)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.uiState.test {
            // Stable state after init
            var currentState = expectMostRecentItem()
            assert(currentState is KunaListUiState.Success && !currentState.isRefreshing)

            // Make the NEXT refresh slow so we can capture the 'true' state
            coEvery { refreshAllKuna() } coAnswers {
                delay(1000)
                Result.Success(Unit)
            }

            viewModel.refresh()

            // Refreshing state
            currentState = awaitItem()
            assert(currentState is KunaListUiState.Success && currentState.isRefreshing)

            // Final state after refresh finishes
            currentState = awaitItem()
            assert(currentState is KunaListUiState.Success && !currentState.isRefreshing)
        }

        coVerify(exactly = 2) { refreshAllKuna() }
    }

    @Test
    fun `given error when refresh then it should show error refresh state`() = runTest {
        every { getAllKuna() } returns MutableStateFlow(Result.Success(KunaTestData.kunaList))
        coEvery { refreshAllKuna() } returns Result.Success(Unit)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.uiState.test {
            expectMostRecentItem()

            val exception = Exception("refresh error")
            coEvery { refreshAllKuna() } coAnswers {
                delay(1000)
                Result.Error(exception)
            }

            viewModel.refresh()

            // Refreshing state
            assert((awaitItem() as KunaListUiState.Success).isRefreshing)

            // Error state
            val errorState = awaitItem()
            assert(errorState is KunaListUiState.Success)
            val successState = errorState as KunaListUiState.Success
            assert(!successState.isRefreshing)
            assertEquals(exception, successState.refreshError)
        }
    }

    @Test
    fun `when clearRefreshError then it should reset refresh error`() = runTest {
        every { getAllKuna() } returns MutableStateFlow(Result.Success(KunaTestData.kunaList))
        coEvery { refreshAllKuna() } returns Result.Success(Unit)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.uiState.test {
            expectMostRecentItem()

            val exception = Exception("refresh error")
            coEvery { refreshAllKuna() } returns Result.Error(exception)

            viewModel.refresh()
            awaitItem() // Success(isRefreshing=true)
            awaitItem() // Success(isRefreshing=false, refreshError=exception)

            viewModel.clearRefreshError()
            val clearedState = awaitItem()
            assert(clearedState is KunaListUiState.Success)
            assert((clearedState as KunaListUiState.Success).refreshError == null)
        }
    }

    @Test
    fun `given error in data flow when init then it should show error state`() = runTest {
        val exception = Exception("data error")
        every { getAllKuna() } returns MutableStateFlow(Result.Error(exception))
        coEvery { refreshAllKuna() } returns Result.Success(Unit)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.uiState.test {
            val errorState = expectMostRecentItem()
            assert(errorState is KunaListUiState.Error)
            assertEquals(exception, (errorState as KunaListUiState.Error).exception)
        }
    }
}
