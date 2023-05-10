package ru.nastyaanastasya.filereader.presentation.helper

import javax.inject.Inject
import ru.nastyaanastasya.filereader.domain.model.FileExtension

class FileExtensionHelper @Inject constructor() {

    fun getFileExtension(value: String): FileExtension {
        return when (value) {
            "doc", "docx" -> FileExtension.DOC
            "jpg", "jpeg" -> FileExtension.JPG
            "mp3" -> FileExtension.MP3
            "mp4" -> FileExtension.MP4
            "pdf" -> FileExtension.PDF
            "png" -> FileExtension.PNG
            "ppt" -> FileExtension.PPT
            "txt" -> FileExtension.TXT
            "xls", "xlsx" -> FileExtension.XLS
            "zip" -> FileExtension.ZIP
            else -> FileExtension.OTHER
        }
    }

}
