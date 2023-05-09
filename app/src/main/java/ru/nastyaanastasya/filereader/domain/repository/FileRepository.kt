package ru.nastyaanastasya.filereader.domain.repository

import ru.nastyaanastasya.filereader.domain.model.ExternalFileDto

interface FileRepository {
    suspend fun getFilesSortByName(): MutableList<ExternalFileDto>

    suspend fun getFilesSortBySizeAsc(): MutableList<ExternalFileDto>
    suspend fun getFilesSortBySizeDesc(): MutableList<ExternalFileDto>

    suspend fun getFilesSortByDateAsc(): MutableList<ExternalFileDto>
    suspend fun getFilesSortByDateDesc(): MutableList<ExternalFileDto>

    suspend fun getFilesSortByExtAsc(): MutableList<ExternalFileDto>
    suspend fun getFilesSortByExtDesc(): MutableList<ExternalFileDto>

    suspend fun getModifiedFiles(): MutableList<ExternalFileDto>
}
