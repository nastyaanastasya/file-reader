package ru.nastyaanastasya.filereader.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.nastyaanastasya.filereader.presentation.helper.FileIconHelper

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
