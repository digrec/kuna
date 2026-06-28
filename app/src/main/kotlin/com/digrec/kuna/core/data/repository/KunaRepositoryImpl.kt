package com.digrec.kuna.core.data.repository

import com.digrec.kuna.core.data.database.dao.KunaDao
import com.digrec.kuna.core.data.database.mapper.toKuna
import com.digrec.kuna.core.data.database.mapper.toKunaEntity
import com.digrec.kuna.core.data.network.mapper.toKuna
import com.digrec.kuna.core.data.network.model.KunaJson
import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.repository.KunaRepository
import com.digrec.kuna.core.domain.result.Result
import com.digrec.kuna.core.domain.result.toResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerializationException
import okio.IOException

class KunaRepositoryImpl(private val dao: KunaDao, private val httpClient: HttpClient) :
    KunaRepository {

    override fun getAllKuna(): Flow<Result<List<Kuna>>> {
        return dao.getKunaEntities().map { entities -> entities.map { it.toKuna() } }.toResult()
    }

    override suspend fun refreshAllKuna(): Result<Unit> {
        return when (val result = fetchAllKuna()) {
            is Result.Success -> upsertKunas(result.data)
            is Result.Error -> result
        }
    }

    private suspend fun fetchAllKuna(): Result<List<Kuna>> {
        return try {
            val kunaList =
                httpClient
                    .get(KunaRepository.ApiEndpoints.KunaList.url)
                    .body<List<KunaJson>>()
                    .map { it.toKuna() }

            Result.Success(kunaList)
        } catch (e: ResponseException) {
            Result.Error(e)
        } catch (e: IOException) {
            Result.Error(e)
        } catch (e: SerializationException) {
            Result.Error(e)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private suspend fun upsertKunas(kunas: List<Kuna>): Result.Success<Unit> {
        val kunaEntities = kunas.map { it.toKunaEntity() }
        return Result.Success(dao.upsertKunaEntities(kunaEntities))
    }
}
