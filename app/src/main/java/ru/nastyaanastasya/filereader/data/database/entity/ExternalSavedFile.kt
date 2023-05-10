package ru.nastyaanastasya.filereader.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "file")
data class ExternalSavedFile(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val hashCode: String,
    val dateOfEditing: Date
)
