package ru.nastyaanastasya.filereader.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.nastyaanastasya.filereader.data.database.AppDatabase

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.invoke(context) as AppDatabase

    @Provides
    fun provideFileDao(database: AppDatabase) = database.fileDao()
}
