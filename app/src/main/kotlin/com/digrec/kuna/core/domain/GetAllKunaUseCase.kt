package com.digrec.kuna.core.domain

import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.repository.KunaRepository
import kotlinx.coroutines.flow.Flow


/**
 * Created by Dejan Igrec
 */
class GetAllKunaUseCase(private val kunaRepository: KunaRepository) {

    operator fun invoke(): Flow<List<Kuna>> {
        return kunaRepository.getAllKuna()
    }
}
