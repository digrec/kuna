package com.digrec.kuna.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.digrec.kuna.core.data.database.model.KunaEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [KunaEntity] access.
 *
 * Created by Dejan Igrec
 */
@Dao
interface KunaDao {

    @Query(value = "SELECT * FROM kunas") fun getKunaEntities(): Flow<List<KunaEntity>>

    @Upsert suspend fun upsertKunaEntities(entities: List<KunaEntity>)
}
