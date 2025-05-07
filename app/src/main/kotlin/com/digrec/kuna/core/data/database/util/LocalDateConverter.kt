package com.digrec.kuna.core.data.database.util

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate


/**
 * Created by Dejan Igrec
 */
class LocalDateConverter {

    @TypeConverter
    fun stringToLocalDate(value: String?): LocalDate? =
        value?.let { LocalDate.parse(it) }

    @TypeConverter
    fun localDateToString(date: LocalDate?): String? =
        date?.toString()
}
