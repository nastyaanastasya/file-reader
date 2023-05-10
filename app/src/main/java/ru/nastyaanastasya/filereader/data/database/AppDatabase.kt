package ru.nastyaanastasya.filereader.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.nastyaanastasya.filereader.data.database.converter.DateConverter
import ru.nastyaanastasya.filereader.data.database.dao.ExternalFileDao
import ru.nastyaanastasya.filereader.data.database.entity.ExternalSavedFile

@Database(
    entities = [ExternalSavedFile::class],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun fileDao(): ExternalFileDao

    companion object {
        private const val DB_NAME = "app.db"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context?) = instance ?: synchronized(LOCK) {
            context?.let {
                buildDatabase(it).apply {
                    instance = this
                }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
