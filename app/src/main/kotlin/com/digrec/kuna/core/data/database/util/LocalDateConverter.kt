package com.digrec.kuna.core.data.database.util

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate


/**
 * Created by Dejan Igrec
 */
class LocalDateConverter {

    @TypeConverter
    fun stringToLocalDate(value: String?): LocalDate? =
        value?.toLocalDate()

    @TypeConverter
    fun localDateToString(date: LocalDate?): String? =
        date?.toString()
}
