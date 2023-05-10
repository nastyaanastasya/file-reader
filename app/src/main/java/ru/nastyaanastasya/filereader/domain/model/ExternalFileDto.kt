package ru.nastyaanastasya.filereader.domain.model

data class ExternalFileDto(
    val hash: String,
    val dateOfEditing: String,
    val fileName: String,
    val fileExtension: FileExtension,
    val path: String,
    val size: String,
)
