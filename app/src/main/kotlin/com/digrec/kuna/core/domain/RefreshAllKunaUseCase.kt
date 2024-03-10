package com.digrec.kuna.core.domain

import com.digrec.kuna.core.domain.repository.KunaRepository
import com.digrec.kuna.core.domain.result.Result


/**
 * Created by Dejan Igrec
 */
class RefreshAllKunaUseCase(private val kunaRepository: KunaRepository) {

    suspend operator fun invoke(): Result<Unit> {
        return kunaRepository.refreshAllKuna()
    }
}
