package com.digrec.kuna.feature.kunalist.ui

import app.cash.turbine.test
import com.digrec.kuna.core.domain.GetAllKunaUseCase
import com.digrec.kuna.core.domain.RefreshAllKunaUseCase
import com.digrec.kuna.core.domain.result.Result
import com.digrec.kuna.core.testing.MainDispatcherRule
import com.digrec.kuna.core.testing.data.KunaTestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    fun `when init then it should load kuna list`() = runTest {
        coEvery { getAllKuna() } returns Result.Success(KunaTestData.kunaList)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.uiState.test {
            val successState = expectMostRecentItem()
            assert(successState is KunaListUiState.Success)
            assertEquals(KunaTestData.kunaList, (successState as KunaListUiState.Success).list)
        }

        coVerify(exactly = 1) { getAllKuna() }
    }

    @Test
    fun `given error when init then it should show error state`() = runTest {
        val exception = Exception("test")
        coEvery { getAllKuna() } returns Result.Error(exception)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.uiState.test {
            val errorState = expectMostRecentItem()
            assert(errorState is KunaListUiState.Error)
            assertEquals(exception, (errorState as KunaListUiState.Error).exception)
        }

        coVerify(exactly = 1) { getAllKuna() }
    }

    @Test
    fun `when refresh then it should update states correctly`() = runTest {
        coEvery { getAllKuna() } returns Result.Success(KunaTestData.kunaList)
        coEvery { refreshAllKuna() } returns Result.Success(Unit)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.uiState.test {
            // Initial load
            assert(awaitItem() is KunaListUiState.Success)

            viewModel.refresh()

            // Refreshing state
            val refreshingState = awaitItem()
            assert(refreshingState is KunaListUiState.Success)
            assert((refreshingState as KunaListUiState.Success).isRefreshing)

            // Final state after load() is called again
            val finalState = awaitItem()
            assert(finalState is KunaListUiState.Success)
            assert(!(finalState as KunaListUiState.Success).isRefreshing)
        }

        coVerify(exactly = 1) { refreshAllKuna() }
        coVerify(exactly = 2) { getAllKuna() } // One on init, one after refresh
    }

    @Test
    fun `given error when refresh then it should show error refresh state`() = runTest {
        coEvery { getAllKuna() } returns Result.Success(KunaTestData.kunaList)
        val exception = Exception("refresh error")
        coEvery { refreshAllKuna() } returns Result.Error(exception)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.uiState.test {
            // Initial load
            assert(awaitItem() is KunaListUiState.Success)

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

        coVerify(exactly = 1) { refreshAllKuna() }
        coVerify(exactly = 1) { getAllKuna() } // only on init, not after error in my current impl
    }

    @Test
    fun `when clearRefreshError then it should reset refresh error`() = runTest {
        coEvery { getAllKuna() } returns Result.Success(KunaTestData.kunaList)
        val exception = Exception("refresh error")
        coEvery { refreshAllKuna() } returns Result.Error(exception)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.uiState.test {
            // Initial load
            assert(awaitItem() is KunaListUiState.Success)

            viewModel.refresh()
            awaitItem() // refreshing
            awaitItem() // error

            viewModel.clearRefreshError()
            val clearedState = awaitItem()
            assert(clearedState is KunaListUiState.Success)
            assert((clearedState as KunaListUiState.Success).refreshError == null)
        }
    }
}
