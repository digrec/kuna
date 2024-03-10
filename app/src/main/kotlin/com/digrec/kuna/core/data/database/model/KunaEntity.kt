package com.digrec.kuna.core.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate


/**
 * Created by Dejan Igrec
 */
@Entity(tableName = "kunas")
data class KunaEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: LocalDate,
    @ColumnInfo(name = "items_issued")
    val itemsIssued: Int,
    @ColumnInfo(name = "image_obverse")
    val imageObverse: String,
    @ColumnInfo(name = "image_reverse")
    val imageReverse: String,
    @ColumnInfo(name = "is_collected")
    val isCollected: Boolean = false,
)
