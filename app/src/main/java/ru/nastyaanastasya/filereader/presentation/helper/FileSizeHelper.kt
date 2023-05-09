package ru.nastyaanastasya.filereader.presentation.helper

import java.io.File
import javax.inject.Inject
import kotlin.math.floor
import kotlin.math.log
import kotlin.math.pow

private const val BYTES = 1024

class FileSizeHelper @Inject constructor() {

    fun getFileSize(path: String): String {
        val bytes = File(path).length()

        val pow = calcPower(bytes)
        val size = calcSize(bytes, pow)
        return getFormattedString(size, pow)
    }

    private fun getFormattedString(size: String, pow: Int): String {
        return when (pow) {
            0 -> "$size B"
            1 -> "$size KB"
            2 -> "$size MB"
            3 -> "$size GB"
            4 -> "$size TB"
            else -> "$size PB"
        }
    }

    private fun calcPower(bytes: Long): Int {
        return floor(log(bytes.toDouble(), BYTES.toDouble())).toInt()
    }

    private fun calcSize(bytes: Long, pow: Int): String {
        return "%.1f".format(bytes / BYTES.toDouble().pow(pow))
    }
}
