package com.digrec.kuna.core.domain

import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.repository.KunaRepository
import com.digrec.kuna.core.domain.result.Result
import kotlinx.coroutines.flow.Flow


/**
 * Created by Dejan Igrec
 */
class GetAllKunaUseCase(private val kunaRepository: KunaRepository) {

    operator fun invoke(): Flow<Result<List<Kuna>>> {
        return kunaRepository.getAllKuna()
    }
}
