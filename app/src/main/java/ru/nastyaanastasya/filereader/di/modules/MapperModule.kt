package ru.nastyaanastasya.filereader.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.File
import ru.nastyaanastasya.filereader.data.mapper.FileMapper
import ru.nastyaanastasya.filereader.domain.mapper.ModelMapper
import ru.nastyaanastasya.filereader.domain.model.ExternalFileDto

@Module
@InstallIn(SingletonComponent::class)
interface MapperModule {

    @Binds
    fun bindsFileMapper(
        impl: FileMapper
    ): ModelMapper<File, ExternalFileDto>
}
