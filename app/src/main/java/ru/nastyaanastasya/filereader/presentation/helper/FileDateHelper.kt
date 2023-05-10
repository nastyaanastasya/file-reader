package ru.nastyaanastasya.filereader.presentation.helper

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FileDateHelper @Inject constructor() {

    fun getFormattedDate(date: Long): String {
        return SimpleDateFormat("MM/dd/yyyy", Locale.US).format(date)
    }
}
