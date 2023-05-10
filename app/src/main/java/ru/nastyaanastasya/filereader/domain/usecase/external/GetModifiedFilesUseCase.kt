package ru.nastyaanastasya.filereader.domain.usecase.external

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.nastyaanastasya.filereader.domain.model.ExternalFileDto
import ru.nastyaanastasya.filereader.domain.model.ExternalSavedFileDto
import ru.nastyaanastasya.filereader.domain.repository.FileRepository

class GetModifiedFilesUseCase @Inject constructor(
    private val fileRepository: FileRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(savedFiles: List<ExternalSavedFileDto>): List<ExternalFileDto> {
        return withContext(dispatcher) {
            fileRepository.getModifiedFiles(savedFiles)
        }
    }
}
