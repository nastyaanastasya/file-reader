package ru.nastyaanastasya.filereader.data.mapper

import javax.inject.Inject
import ru.nastyaanastasya.filereader.data.database.entity.ExternalSavedFile
import ru.nastyaanastasya.filereader.domain.mapper.ModelMapper
import ru.nastyaanastasya.filereader.domain.model.ExternalSavedFileDto

class ExternalSavedFileMapper @Inject constructor(): ModelMapper<ExternalSavedFile, ExternalSavedFileDto> {

    override fun map(source: ExternalSavedFile) = ExternalSavedFileDto(
        hashCode = source.hashCode,
        dateOfEditing = source.dateOfEditing
    )
}
