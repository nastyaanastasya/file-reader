package ru.nastyaanastasya.filereader.data.util

import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest
import javax.inject.Inject

class HashUtil @Inject constructor() {

    fun hash(path: String): String {
        return sha256(path).toHex()
    }

    private fun sha256(str: String): ByteArray {
        return MessageDigest.getInstance("SHA-256").digest(str.toByteArray(UTF_8))
    }

    private fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }
}
