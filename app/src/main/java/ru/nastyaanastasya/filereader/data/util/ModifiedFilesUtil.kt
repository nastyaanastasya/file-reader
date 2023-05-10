package ru.nastyaanastasya.filereader.data.util

import java.io.File
import javax.inject.Inject
import ru.nastyaanastasya.filereader.domain.model.ExternalSavedFileDto

class ModifiedFilesUtil @Inject constructor(
    private val hashUtil: HashUtil
) {

    fun searchModifiedFiles(oldData: List<ExternalSavedFileDto>, newData: List<File>): List<File> {
        val modifiedFiles = mutableListOf<File>()

        newData.forEach { file ->
            val oldFile = oldData.find {
                it.hashCode == hashUtil.hash(file.absolutePath)
            }
            if (oldFile != null && oldFile.dateOfEditing.time != file.lastModified()) {
                modifiedFiles.add(file)
            }
        }
        return modifiedFiles
    }
}
