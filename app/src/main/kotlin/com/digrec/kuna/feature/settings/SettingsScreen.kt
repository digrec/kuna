package com.digrec.kuna.feature.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digrec.kuna.R
import com.digrec.kuna.core.ui.theme.KunaTheme
import org.koin.androidx.compose.getViewModel


@Composable
fun SettingsRoute(
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = getViewModel(),
    onClickBack: () -> Unit,
) {
    // get the state from the settingsViewModel

    SettingsScreen(
        modifier = modifier,
        versionCode = 1,
        versionName = "0.1",
        onClickBack = onClickBack,
    )
}

/**
 * Created by Dejan Igrec
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(
    modifier: Modifier,
    versionCode: Int,
    versionName: String,
    onClickBack: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_settings),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                navigationIcon = {
                    IconButton(onClick = { onClickBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                },
            )
        },
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(R.string.app_version),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(text = "v$versionName+$versionCode")
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    KunaTheme {
        SettingsScreen(
            modifier = Modifier.fillMaxSize(),
            versionCode = 1,
            versionName = "0.1",
            onClickBack = {},
        )
    }
}
