package ru.nastyaanastasya.filereader.domain.repository

import ru.nastyaanastasya.filereader.domain.model.ExternalSavedFileDto

interface ExternalFileRepository {
    suspend fun getAllSavedFiles(): List<ExternalSavedFileDto>
    suspend fun saveNewData(newData: List<ExternalSavedFileDto>)
    suspend fun removeOldData()
}
