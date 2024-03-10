package com.digrec.kuna.core.domain

import com.digrec.kuna.core.domain.model.Kuna
import com.digrec.kuna.core.domain.repository.KunaRepository
import com.digrec.kuna.core.domain.result.Result


/**
 * Created by Dejan Igrec
 */
class GetAllKunaUseCase(private val kunaRepository: KunaRepository) {

    suspend operator fun invoke(): Result<List<Kuna>> {
        return kunaRepository.getAllKuna()
    }
}
