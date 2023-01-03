package com.digrec.kuna.core.ui.kunaList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digrec.kuna.R
import com.digrec.kuna.ui.theme.KunaTheme
import org.koin.androidx.compose.getViewModel


@Composable
fun KunaListRoute(
    modifier: Modifier = Modifier,
    kunaListViewModel: KunaListViewModel = getViewModel(),
    onClickSettings: () -> Unit,
) {
    // get the state from the kunaListViewModel

    KunaListScreen(onClickSettings)
}

/**
 * Created by Dejan Igrec
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun KunaListScreen(
    onClickSettings: () -> Unit,
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
        Box(modifier = Modifier.padding(padding)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Greeting(name = stringResource(R.string.app_name))
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun KunaListScreenPreview() {
    KunaTheme {
        KunaListScreen(onClickSettings = {})
    }
}
