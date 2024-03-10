package com.digrec.kuna.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.digrec.kuna.core.data.database.dao.KunaDao
import com.digrec.kuna.core.data.database.model.KunaEntity
import com.digrec.kuna.core.data.database.util.LocalDateConverter


/**
 * Created by Dejan Igrec
 */
@Database(
    entities = [KunaEntity::class],
    version = 1,
)
@TypeConverters(LocalDateConverter::class)
abstract class KunaDatabase : RoomDatabase() {

    abstract fun kunaDao(): KunaDao

    companion object {
        const val DB_FILE_NAME = "kuna-database"
    }
}
