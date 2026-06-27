package com.digrec.kuna.feature.kunalist.ui

import app.cash.turbine.test
import com.digrec.kuna.core.domain.GetAllKunaUseCase
import com.digrec.kuna.core.domain.RefreshAllKunaUseCase
import com.digrec.kuna.core.domain.model.previewKunaList
import com.digrec.kuna.core.domain.result.Result
import com.digrec.kuna.core.testing.MainDispatcherRule
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
        coEvery { getAllKuna() } returns Result.Success(previewKunaList)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.listUiState.test {
            val successState = expectMostRecentItem()
            assert(successState is ListUiState.Success)
            assertEquals(previewKunaList, (successState as ListUiState.Success).list)
        }

        coVerify(exactly = 1) { getAllKuna() }
    }

    @Test
    fun `given error when init then it should show error state`() = runTest {
        val exception = Exception("test")
        coEvery { getAllKuna() } returns Result.Error(exception)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.listUiState.test {
            val errorState = expectMostRecentItem()
            assert(errorState is ListUiState.Error)
            assertEquals(exception, (errorState as ListUiState.Error).exception)
        }

        coVerify(exactly = 1) { getAllKuna() }
    }

    @Test
    fun `when refresh then it should update states correctly`() = runTest {
        coEvery { getAllKuna() } returns Result.Success(previewKunaList)
        coEvery { refreshAllKuna() } returns Result.Success(Unit)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.refreshState.test {
            assertEquals(RefreshState.NotRefreshing, awaitItem())
            viewModel.refresh()
            assertEquals(RefreshState.Refreshing, awaitItem())
            assertEquals(RefreshState.Success, awaitItem())
        }

        coVerify(exactly = 1) { refreshAllKuna() }
        coVerify(exactly = 2) { getAllKuna() } // One on init, one after refresh
    }

    @Test
    fun `given error when refresh then it should show error refresh state`() = runTest {
        coEvery { getAllKuna() } returns Result.Success(previewKunaList)
        val exception = Exception("refresh error")
        coEvery { refreshAllKuna() } returns Result.Error(exception)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.refreshState.test {
            assertEquals(RefreshState.NotRefreshing, awaitItem())
            viewModel.refresh()
            assertEquals(RefreshState.Refreshing, awaitItem())
            val errorState = awaitItem()
            assert(errorState is RefreshState.Error)
            assertEquals(exception, (errorState as RefreshState.Error).exception)
        }

        coVerify(exactly = 1) { refreshAllKuna() }
        coVerify(exactly = 2) {
            getAllKuna()
        } // One on init, one after refresh even if refresh fails
    }

    @Test
    fun `when refreshed then it should reset refresh state`() = runTest {
        coEvery { getAllKuna() } returns Result.Success(previewKunaList)
        coEvery { refreshAllKuna() } returns Result.Success(Unit)

        viewModel = KunaListViewModel(getAllKuna, refreshAllKuna)

        viewModel.refreshState.test {
            assertEquals(RefreshState.NotRefreshing, awaitItem())

            // 1. Trigger refresh to move state to Success
            viewModel.refresh()
            assertEquals(RefreshState.Refreshing, awaitItem())
            assertEquals(RefreshState.Success, awaitItem())

            // 2. Call refreshed() and assert it returns to NotRefreshing
            viewModel.refreshed()
            assertEquals(RefreshState.NotRefreshing, awaitItem())
        }
    }
}
