package ru.nastyaanastasya.filereader.domain.usecase.db

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.nastyaanastasya.filereader.domain.repository.ExternalFileRepository

class DeleteOldDataUseCase @Inject constructor(
    private val savedFileRepository: ExternalFileRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke() {
        return withContext(dispatcher) {
            savedFileRepository.removeOldData()
        }
    }
}
