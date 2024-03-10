package com.digrec.kuna.core.data.repository

import com.digrec.kuna.core.data.database.dao.KunaDao
import com.digrec.kuna.core.data.database.mapper.toKuna
import com.digrec.kuna.core.data.database.mapper.toKunaEntity
import com.digrec.kuna.core.data.network.mapper.toKuna
import com.digrec.kuna.core.data.network.model.KunaJson
import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.repository.KunaRepository
import com.digrec.kuna.core.domain.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


class KunaRepositoryImpl(
    private val dao: KunaDao,
    private val httpClient: HttpClient,
) : KunaRepository {

    override suspend fun getAllKuna(): Result<List<Kuna>> {
        return try {
            Result.Success(dao.getKunaEntities().map { it.toKuna() })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun refreshAllKuna(): Result<Unit> {
        return when (val result = fetchAllKuna()) {
            is Result.Success -> upsertKunas(result.data)
            is Result.Error -> result
        }
    }

    private suspend fun fetchAllKuna(): Result<List<Kuna>> {
        return try {
            val kunaList = httpClient.get(KunaRepository.ApiEndpoints.KunaList.url)
                .body<List<KunaJson>>()
                .map { it.toKuna() }

            Result.Success(kunaList)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private suspend fun upsertKunas(kunas: List<Kuna>): Result.Success<Unit> {
        val kunaEntities = kunas.map { it.toKunaEntity() }
        return Result.Success(dao.upsertKunaEntities(kunaEntities))
    }
}
