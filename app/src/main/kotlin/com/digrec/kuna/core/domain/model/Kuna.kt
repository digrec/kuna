package com.digrec.kuna.core.domain.model

import java.time.LocalDate


/**
 * 25 Kuna coin entity.
 */
data class Kuna(
    val id: String,
    val title: String,
    val releaseDate: LocalDate,
    val itemsIssued: Int,
    val isCollected: Boolean = false,
)

val previewKunaList = listOf(
    Kuna(
        id = "1",
        title = "25 kuna - Croatian Danube Region",
        releaseDate = LocalDate.of(1997, 5, 28),
        itemsIssued = 300_000,
    ),
    Kuna(
        id = "2",
        title = "25 kuna - The First Croatian Esperanto Congress",
        releaseDate = LocalDate.of(1997, 6, 24),
        itemsIssued = 300_000,
    ),
    Kuna(
        id = "3",
        title = "25 kuna - Admission of the Republic of Croatia to the UNO",
        releaseDate = LocalDate.of(1997, 10, 27),
        itemsIssued = 300_000,
    ),
    Kuna(
        id = "4",
        title = "25 kuna - EXPO - Lisbon, 1998",
        releaseDate = LocalDate.of(1998, 6, 26),
        itemsIssued = 300_000,
    ),
    Kuna(
        id = "5",
        title = "25 kuna - EURO",
        releaseDate = LocalDate.of(1999, 12, 27),
        itemsIssued = 300_000,
    ),
    Kuna(
        id = "6",
        title = "25 kuna - 2000",
        releaseDate = LocalDate.of(2000, 11, 7),
        itemsIssued = 300_000,
    ),
    Kuna(
        id = "7",
        title = "25 kuna - 10th Anniversary of the International Recognition of the Republic of Croatia",
        releaseDate = LocalDate.of(2005, 8, 4),
        itemsIssued = 200_000,
    ),
    Kuna(
        id = "8",
        title = "25 kuna - Republic of Croatia - EU Membership Candidate, 18 June 2004",
        releaseDate = LocalDate.of(2005, 8, 4),
        itemsIssued = 30_000,
    ),
    Kuna(
        id = "9",
        title = "25 kuna - Annual Meeting of the European Bank for Reconstruction and Development, Zagreb 2010",
        releaseDate = LocalDate.of(2010, 5, 12),
        itemsIssued = 20_000,
    ),
    Kuna(
        id = "10",
        title = "25 kuna - Treaty of Accession of the Republic of Croatia to the European Union, 9 December 2011",
        releaseDate = LocalDate.of(2012, 12, 3),
        itemsIssued = 300_000,
    ),
    Kuna(
        id = "11",
        title = "25 kuna - Republic of Croatia - a Member of the European Union - 1 July 2013",
        releaseDate = LocalDate.of(2013, 7, 1),
        itemsIssued = 20_000,
    ),
    Kuna(
        id = "12",
        title = "25 kuna – 25th Anniversary of Independence of the Republic of Croatia, 8 October 1991 – 8 October 2016",
        releaseDate = LocalDate.of(2016, 10, 7),
        itemsIssued = 50_000,
    ),
    Kuna(
        id = "13",
        title = "25 kuna – 25th Anniversary of the Admission of the Republic of Croatia to Membership in the United Nations",
        releaseDate = LocalDate.of(2017, 5, 22),
        itemsIssued = 20_000,
    ),
    Kuna(
        id = "14",
        title = "25 kuna – 25th Anniversary of the Introduction of the Kuna as the Monetary Unit of the Republic of Croatia, 30 May 1994 – 30 May 2019",
        releaseDate = LocalDate.of(2019, 5, 30),
        itemsIssued = 30_000,
    ),
    Kuna(
        id = "15",
        title = "25 kuna – 350th Anniversary of the Founding of the University of Zagreb, 1669 – 2019",
        releaseDate = LocalDate.of(2019, 11, 4),
        itemsIssued = 20_000,
    ),
    Kuna(
        id = "16",
        title = "25 kuna – Croatian Presidency of the Council of the European Union 2020",
        releaseDate = LocalDate.of(2020, 1, 15),
        itemsIssued = 30_000,
    ),
    Kuna(
        id = "17",
        title = "25 kuna – 75th Anniversary of the Founding of the Croatian Association of Technical Culture, 1946 – 2021",
        releaseDate = LocalDate.of(2021, 6, 23),
        itemsIssued = 50_000,
    ),
    Kuna(
        id = "18",
        title = "25 kuna – World Children's Day, 20 November 2021",
        releaseDate = LocalDate.of(2021, 11, 19),
        itemsIssued = 50_000,
    ),
    Kuna(
        id = "19",
        title = "25 kuna – Pelješac Bridge, 2022",
        releaseDate = LocalDate.of(2022, 7, 26),
        itemsIssued = 30_000,
    ),
)
