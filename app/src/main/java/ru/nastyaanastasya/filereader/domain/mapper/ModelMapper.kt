package ru.nastyaanastasya.filereader.domain.mapper

interface ModelMapper<S, D> {
    fun map(source: S): D
}
