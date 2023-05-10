package ru.nastyaanastasya.filereader.domain.usecase.db

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.nastyaanastasya.filereader.domain.model.ExternalSavedFileDto
import ru.nastyaanastasya.filereader.domain.repository.ExternalFileRepository

class SaveNewDataUseCase @Inject constructor(
    private val savedFileRepository: ExternalFileRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(newData: List<ExternalSavedFileDto>) {
        return withContext(dispatcher) {
            savedFileRepository.saveNewData(newData)
        }
    }
}
