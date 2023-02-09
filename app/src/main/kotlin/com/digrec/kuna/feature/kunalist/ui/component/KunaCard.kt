package com.digrec.kuna.feature.kunalist.ui.component

import android.icu.text.NumberFormat
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digrec.kuna.R
import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.model.previewKunaList
import com.digrec.kuna.core.ui.theme.KunaTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


/**
 * [KunaCard] card used on Kuna list screen.
 *
 * @param kuna A Kuna coin.
 * @param isChecked Whether the toggle button is currently checked.
 * @param onToggleCheckmark Called when the user toggles the checkmark button.
 * @param onClick Called when the user clicks the card.
 * @param modifier Modifier to be applied to the toggle button.
 *
 * Created by Dejan Igrec
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KunaCard(
    kuna: Kuna,
    isChecked: Boolean,
    onToggleCheckmark: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val clickActionLabel = stringResource(R.string.card_tap_action)
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        // Use custom label for accessibility services to communicate button's action to user.
        modifier = modifier.semantics {
            onClick(label = clickActionLabel, action = null)
        }
    ) {
        Column {
            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                Column {
                    Row {
                        KunaTitle(
                            kuna.title,
                            modifier = Modifier.fillMaxWidth((.8f)),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        CheckmarkButton(isChecked, onToggleCheckmark)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        KunaReleaseDate(
                            kuna.releaseDate,
                            modifier = Modifier.fillMaxWidth((.8f))
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        KunaItemsIssued(itemsIssued = kuna.itemsIssued)
                    }
                }
            }
        }
    }
}

@Composable
fun KunaTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier,
    )
}

@Composable
fun CheckmarkButton(
    isChecked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    KunaIconToggleButton(
        checked = isChecked,
        onCheckedChange = { onClick() },
        modifier = modifier,
        icon = { // check
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = stringResource(R.string.app_settings),
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        },
        checkedIcon = { // un-check
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = stringResource(R.string.app_settings),
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
    )
}

@Composable
fun KunaReleaseDate(
    releaseDate: LocalDate,
    modifier: Modifier = Modifier,
) {
    Text(
        text = releaseDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}

@Composable
fun KunaItemsIssued(
    itemsIssued: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = NumberFormat.getInstance().format(itemsIssued),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier,
    )
}

@Preview
@Composable
fun KunaCardPreview() {
    KunaTheme {
        KunaCard(
            kuna = previewKunaList[0],
            isChecked = true,
            onToggleCheckmark = { },
            onClick = { },
        )
    }
}
