package ru.nastyaanastasya.filereader.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nastyaanastasya.filereader.data.repository.ExternalFileRepositoryImpl
import ru.nastyaanastasya.filereader.data.repository.FileRepositoryImpl
import ru.nastyaanastasya.filereader.domain.repository.ExternalFileRepository
import ru.nastyaanastasya.filereader.domain.repository.FileRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun bindsFileRepository(
        impl: FileRepositoryImpl
    ): FileRepository

    @Binds
    fun bindsExternalFileRepository(
        impl: ExternalFileRepositoryImpl
    ): ExternalFileRepository
}
