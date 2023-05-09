package ru.nastyaanastasya.filereader.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.nastyaanastasya.filereader.domain.model.ExternalFileDto
import ru.nastyaanastasya.filereader.domain.repository.FileRepository

class GetFilesSortByExtAscUseCase @Inject constructor(
    private val fileRepository: FileRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): List<ExternalFileDto> {
        return withContext(dispatcher) {
            fileRepository.getFilesSortByExtAsc()
        }
    }
}
