package ru.nastyaanastasya.filereader.data.repository

import android.os.Environment
import java.io.File
import javax.inject.Inject
import ru.nastyaanastasya.filereader.data.util.ModifiedFilesUtil
import ru.nastyaanastasya.filereader.domain.mapper.ModelMapper
import ru.nastyaanastasya.filereader.domain.model.ExternalFileDto
import ru.nastyaanastasya.filereader.domain.model.ExternalSavedFileDto
import ru.nastyaanastasya.filereader.domain.repository.FileRepository

private val PATH = Environment.getExternalStorageDirectory().absolutePath

class FileRepositoryImpl @Inject constructor(
    private val mapper: ModelMapper<File, ExternalFileDto>,
    private val modifiedFilesUtil: ModifiedFilesUtil
) : FileRepository {

    override suspend fun getFilesSortByName(): MutableList<ExternalFileDto> {
        TempFiles.files = getRawFiles()

        return mapFiles(
            TempFiles.files.sortedBy {
                it.name
            }
        )
    }

    override suspend fun getFilesSortBySizeAsc(): MutableList<ExternalFileDto> {
        return mapFiles(
            TempFiles.files.sortedBy {
                it.length()
            }
        )
    }

    override suspend fun getFilesSortBySizeDesc(): MutableList<ExternalFileDto> {
        return mapFiles(
            TempFiles.files.sortedBy {
                it.length()
            }.reversed()
        )
    }

    override suspend fun getFilesSortByDateAsc(): MutableList<ExternalFileDto> {
        return mapFiles(
            TempFiles.files.sortedBy {
                it.lastModified()
            }
        )
    }

    override suspend fun getFilesSortByDateDesc(): MutableList<ExternalFileDto> {
        return mapFiles(
            TempFiles.files.sortedBy {
                it.lastModified()
            }.reversed()
        )
    }

    override suspend fun getFilesSortByExtAsc(): MutableList<ExternalFileDto> {
        return mapFiles(
            TempFiles.files.sortedBy {
                it.extension
            }
        )
    }

    override suspend fun getFilesSortByExtDesc(): MutableList<ExternalFileDto> {
        return mapFiles(
            TempFiles.files.sortedBy {
                it.extension
            }.reversed()
        )
    }

    override suspend fun getModifiedFiles(savedFiles: List<ExternalSavedFileDto>): MutableList<ExternalFileDto> {
        return mapFiles(
            modifiedFilesUtil.searchModifiedFiles(savedFiles, TempFiles.files)
        )
    }

    private fun mapFiles(files: List<File>?): MutableList<ExternalFileDto> {
        val externalFiles = mutableListOf<ExternalFileDto>()

        files?.forEach {
            externalFiles.add(
                mapper.map(it)
            )
        }
        return externalFiles
    }

    private fun getRawFiles(): List<File> {
        return File(PATH).walkTopDown().toList().filter {
            it.isFile
        }.filterNot {
            it.name.startsWith(".")
        }.filterNot {
            it.extension == ""
        }
    }
}

object TempFiles {
    var files = listOf<File>()
}
