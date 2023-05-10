package ru.nastyaanastasya.filereader.presentation.helper

import javax.inject.Inject
import ru.nastyaanastasya.filereader.R
import ru.nastyaanastasya.filereader.domain.model.FileExtension

class FileIconHelper @Inject constructor() {

    fun getIcon(ext: FileExtension): Int =
        when (ext) {
            FileExtension.DOC -> R.drawable.doc
            FileExtension.JPG -> R.drawable.jpg
            FileExtension.MP3 -> R.drawable.mp3
            FileExtension.MP4 -> R.drawable.mp4
            FileExtension.PDF -> R.drawable.pdf
            FileExtension.PNG -> R.drawable.png
            FileExtension.PPT -> R.drawable.ppt
            FileExtension.TXT -> R.drawable.txt
            FileExtension.XLS -> R.drawable.xls
            FileExtension.ZIP -> R.drawable.zip
            else -> R.drawable.file
        }
}
