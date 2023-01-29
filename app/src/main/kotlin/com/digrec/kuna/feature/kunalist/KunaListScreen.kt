package com.digrec.kuna.feature.kunalist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.digrec.kuna.R
import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.model.previewKunaList
import com.digrec.kuna.ui.theme.KunaTheme
import org.koin.androidx.compose.getViewModel


sealed interface ListUiState {

    object Loading : ListUiState

    data class Success(val list: List<Kuna>) : ListUiState
}

@Composable
fun KunaListRoute(
    modifier: Modifier = Modifier,
    viewModel: KunaListViewModel = getViewModel(),
    onClickSettings: () -> Unit,
) {
    val listState: ListUiState by viewModel.listUiState.collectAsStateWithLifecycle()

    KunaListScreen(
        listState = listState,
        onClickSettings = onClickSettings,
        removeFromCollection = viewModel::removeFromCollection
    )
}

/**
 * Created by Dejan Igrec
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun KunaListScreen(
    listState: ListUiState,
    onClickSettings: () -> Unit,
    removeFromCollection: (String) -> Unit,
) {
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
    ) { padding ->
        when (listState) {
            ListUiState.Loading -> EmptyState(padding)
            is ListUiState.Success -> KunaGrid(
                listState = listState,
                removeFromCollection = removeFromCollection,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
private fun KunaGrid(
    listState: ListUiState,
    removeFromCollection: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollableState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        state = scrollableState,
        modifier = modifier.fillMaxSize()
    ) {
        kunaList(
            listState = listState,
            onCoinCheckedChanged = { id, _ -> removeFromCollection(id) },
        )
        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
        }
    }
}

@Composable
private fun EmptyState(padding: PaddingValues) {
    Box(modifier = Modifier.padding(padding)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Hello ${stringResource(R.string.app_name)}!")
        }
    }
}

@Preview
@Composable
fun KunaListScreenLoadingPreview() {
    KunaTheme {
        KunaListScreen(
            listState = ListUiState.Loading,
            onClickSettings = {},
            removeFromCollection = {},
        )
    }
}

@Preview
@Composable
fun KunaListScreenPreview() {
    KunaTheme {
        KunaListScreen(
            listState = ListUiState.Success(previewKunaList),
            onClickSettings = {},
            removeFromCollection = {},
        )
    }
}
