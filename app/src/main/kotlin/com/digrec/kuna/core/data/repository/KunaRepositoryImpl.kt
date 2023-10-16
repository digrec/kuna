package com.digrec.kuna.core.data.repository

import com.digrec.kuna.core.data.network.mapper.toKuna
import com.digrec.kuna.core.data.network.model.KunaJson
import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.repository.KunaRepository
import com.digrec.kuna.core.domain.result.Result
import com.digrec.kuna.core.domain.result.toResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext


class KunaRepositoryImpl(
    private val httpClient: HttpClient,
    private val ioDispatcher: CoroutineContext,
) : KunaRepository {

    override fun getAllKuna(): Flow<Result<List<Kuna>>> = flow {
        emit(
            httpClient.get(KunaRepository.ApiEndpoints.KunaList.url)
                .body<List<KunaJson>>()
                .map { it.toKuna() }
        )
    }.toResult()
        .flowOn(ioDispatcher)
}
