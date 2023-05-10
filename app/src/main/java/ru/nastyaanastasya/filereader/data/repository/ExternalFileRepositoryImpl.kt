package ru.nastyaanastasya.filereader.data.repository

import javax.inject.Inject
import ru.nastyaanastasya.filereader.data.database.dao.ExternalFileDao
import ru.nastyaanastasya.filereader.data.database.entity.ExternalSavedFile
import ru.nastyaanastasya.filereader.domain.model.ExternalSavedFileDto
import ru.nastyaanastasya.filereader.domain.repository.ExternalFileRepository

class ExternalFileRepositoryImpl @Inject constructor(
    private val fileDao: ExternalFileDao
) : ExternalFileRepository {

    override suspend fun getAllSavedFiles(): List<ExternalSavedFileDto> {
        val savedFiles = mutableListOf<ExternalSavedFileDto>()

        fileDao.getAllFiles().forEach {
            savedFiles.add(
                ExternalSavedFileDto(it.hashCode, it.dateOfEditing)
            )
        }
        return savedFiles
    }

    override suspend fun saveNewData(newData: List<ExternalSavedFileDto>) {
        newData.forEach {
            fileDao.save(
                ExternalSavedFile(0, it.hashCode, it.dateOfEditing)
            )
        }
    }

    override suspend fun removeOldData() = fileDao.deleteOldData()
}
