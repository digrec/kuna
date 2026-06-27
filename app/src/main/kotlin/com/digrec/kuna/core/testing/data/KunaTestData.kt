package com.digrec.kuna.core.testing.data

import com.digrec.kuna.core.domain.model.Kuna
import kotlinx.datetime.LocalDate

/**
 * Reusable mock [Kuna] data for tests and previews.
 *
 * Created by Dejan Igrec
 */
object KunaTestData {
    val kunaList =
        listOf(
            Kuna(
                id = 1,
                title = "Croatian Danube Region",
                releaseDate = LocalDate(1997, 5, 28),
                itemsIssued = 300_000,
                imageObverse = "25kn-hrvatsko-podunavlje-1997-l.png",
                imageReverse = "25kn-n.png",
            ),
            Kuna(
                id = 2,
                title = "The First Croatian Esperanto Congress",
                releaseDate = LocalDate(1997, 6, 24),
                itemsIssued = 300_000,
                imageObverse = "25kn-prvi-kongres-hr-esperantista-l.png",
                imageReverse = "25kn-n.png",
            ),
            Kuna(
                id = 3,
                title = "Admission of the Republic of Croatia to the UNO",
                releaseDate = LocalDate(1997, 10, 27),
                itemsIssued = 300_000,
                imageObverse = "25kn-primanje-rh-u-oun-1997-l.png",
                imageReverse = "25kn-n.png",
            ),
        )

    val singleKuna = kunaList.first()
}
