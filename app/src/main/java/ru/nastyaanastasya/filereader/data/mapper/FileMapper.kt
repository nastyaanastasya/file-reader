package ru.nastyaanastasya.filereader.data.mapper

import java.io.File
import javax.inject.Inject
import ru.nastyaanastasya.filereader.data.util.HashUtil
import ru.nastyaanastasya.filereader.domain.mapper.ModelMapper
import ru.nastyaanastasya.filereader.domain.model.ExternalFileDto
import ru.nastyaanastasya.filereader.presentation.helper.FileDateHelper
import ru.nastyaanastasya.filereader.presentation.helper.FileExtensionHelper
import ru.nastyaanastasya.filereader.presentation.helper.FileSizeHelper

class FileMapper @Inject constructor(
    private val fileExtensionHelper: FileExtensionHelper,
    private val fileSizeHelper: FileSizeHelper,
    private val fileDateHelper: FileDateHelper,
    private val hashUtil: HashUtil
) : ModelMapper<File, ExternalFileDto> {

    override fun map(source: File) = ExternalFileDto(
        hash = hashUtil.hash(source.absolutePath),
        dateOfEditing = fileDateHelper.getFormattedDate(source.lastModified()),
        fileName = source.name,
        fileExtension = fileExtensionHelper.getFileExtension(
            source.extension
        ),
        path = source.absolutePath,
        size = fileSizeHelper.getFileSize(source.absolutePath),
    )
}
