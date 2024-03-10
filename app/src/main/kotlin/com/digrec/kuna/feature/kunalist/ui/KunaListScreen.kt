package com.digrec.kuna.feature.kunalist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.digrec.kuna.R
import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.model.previewKunaList
import com.digrec.kuna.core.ui.theme.KunaTheme
import com.digrec.kuna.feature.kunalist.ui.component.kunaList
import org.koin.androidx.compose.getViewModel

sealed interface RefreshState {
    data object NotRefreshing : RefreshState
    data object Refreshing : RefreshState
    data object Success : RefreshState
    data class Error(val exception: Throwable?) : RefreshState
}

sealed interface ListUiState {

    data object NotLoading : ListUiState

    data object Loading : ListUiState

    data class Success(val list: List<Kuna>) : ListUiState

    data class Error(val exception: Throwable? = null) : ListUiState
}

@Composable
fun KunaListRoute(
    modifier: Modifier = Modifier,
    viewModel: KunaListViewModel = getViewModel(),
    onClickSettings: () -> Unit,
) {
    val refreshState: RefreshState by viewModel.refreshState.collectAsStateWithLifecycle()
    val listState: ListUiState by viewModel.listUiState.collectAsStateWithLifecycle()

    KunaListScreen(
        refreshState = refreshState,
        listState = listState,
        onClickSettings = onClickSettings,
        refresh = viewModel::refresh,
        refreshed = viewModel::refreshed,
        removeFromCollection = viewModel::removeFromCollection
    )
}

/**
 * Created by Dejan Igrec
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun KunaListScreen(
    refreshState: RefreshState,
    listState: ListUiState,
    onClickSettings: () -> Unit,
    refresh: () -> Unit,
    refreshed: () -> Unit,
    removeFromCollection: (Int) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                actions = {
                    IconButton(onClick = { onClickSettings() }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = stringResource(R.string.app_settings),
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { padding ->
        when (refreshState) {
            is RefreshState.Refreshing -> {}

            is RefreshState.Error -> {
                val snackbarMessage = stringResource(R.string.refresh_list_failed)
                val snackbarAction = stringResource(R.string.retry)

                LaunchedEffect(refreshState) {
                    val result = snackbarHostState.showSnackbar(
                        message = snackbarMessage,
                        actionLabel = snackbarAction,
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        refresh()
                    }
                }
            }

            else -> {}
        }

        when (listState) {
            is ListUiState.NotLoading -> Unit

            is ListUiState.Loading -> Unit

            is ListUiState.Success -> KunaGrid(
                listState = listState,
                refreshState = refreshState,
                refresh = refresh,
                refreshed = refreshed,
                removeFromCollection = removeFromCollection,
                modifier = Modifier.padding(padding)
            )

            is ListUiState.Error -> {
                ErrorState(padding)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun KunaGrid(
    listState: ListUiState,
    refreshState: RefreshState,
    refresh: () -> Unit,
    refreshed: () -> Unit,
    removeFromCollection: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing
        && (refreshState is RefreshState.NotRefreshing || refreshState is RefreshState.Error)
    ) {
        refresh()
    }

    if (refreshState is RefreshState.Success) {
        pullToRefreshState.endRefresh()
        refreshed()
    }

    if (refreshState is RefreshState.Error) {
        pullToRefreshState.endRefresh()
    }

    val scrollableState = rememberLazyGridState()
    Box(modifier = modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            state = scrollableState,
            modifier = Modifier.fillMaxSize()
        ) {
            kunaList(
                listState = listState,
                onCoinCheckedChanged = { id, _ -> removeFromCollection(id) },
            )
            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
            }
        }
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullToRefreshState,
        )
    }
}

@Composable
private fun ErrorState(padding: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.loading_list_failed))
        }
    }
}

@Preview
@Composable
fun KunaListScreenLoadingPreview() {
    KunaTheme {
        KunaListScreen(
            refreshState = RefreshState.NotRefreshing,
            listState = ListUiState.Loading,
            onClickSettings = {},
            refresh = {},
            refreshed = {},
            removeFromCollection = {},
        )
    }
}

@Preview
@Composable
fun KunaListScreenPreview() {
    KunaTheme {
        KunaListScreen(
            refreshState = RefreshState.NotRefreshing,
            listState = ListUiState.Success(previewKunaList),
            onClickSettings = {},
            refresh = {},
            refreshed = {},
            removeFromCollection = {},
        )
    }
}
