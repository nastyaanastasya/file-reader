package ru.nastyaanastasya.filereader.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.nastyaanastasya.filereader.data.database.entity.ExternalSavedFile
import ru.nastyaanastasya.filereader.domain.model.ExternalSavedFileDto

@Dao
interface ExternalFileDao {

    @Insert
    suspend fun save(file: ExternalSavedFile)

    @Query("SELECT * FROM file")
    suspend fun getAllFiles(): List<ExternalSavedFile>

    @Query("DELETE FROM file")
    suspend fun deleteOldData()
}
