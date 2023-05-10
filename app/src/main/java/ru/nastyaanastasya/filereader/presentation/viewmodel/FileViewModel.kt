package ru.nastyaanastasya.filereader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.nastyaanastasya.filereader.domain.model.ExternalFileDto
import ru.nastyaanastasya.filereader.domain.model.ExternalSavedFileDto
import ru.nastyaanastasya.filereader.domain.usecase.external.GetFilesSortByDateAscUseCase
import ru.nastyaanastasya.filereader.domain.usecase.external.GetFilesSortByDateDescUseCase
import ru.nastyaanastasya.filereader.domain.usecase.external.GetFilesSortByExtAscUseCase
import ru.nastyaanastasya.filereader.domain.usecase.external.GetFilesSortByExtDescUseCase
import ru.nastyaanastasya.filereader.domain.usecase.external.GetFilesSortByNameUseCase
import ru.nastyaanastasya.filereader.domain.usecase.external.GetFilesSortBySizeAscUseCase
import ru.nastyaanastasya.filereader.domain.usecase.external.GetFilesSortBySizeDescUseCase
import ru.nastyaanastasya.filereader.domain.usecase.external.GetModifiedFilesUseCase

@HiltViewModel
class FileViewModel @Inject constructor(
    private val getFilesSortByNameUseCase: GetFilesSortByNameUseCase,
    private val getFilesSortBySizeAscUseCase: GetFilesSortBySizeAscUseCase,
    private val getFilesSortBySizeDescUseCase: GetFilesSortBySizeDescUseCase,
    private val getFilesSortByDateAscUseCase: GetFilesSortByDateAscUseCase,
    private val getFilesSortByDateDescUseCase: GetFilesSortByDateDescUseCase,
    private val getFilesSortByExtAscUseCase: GetFilesSortByExtAscUseCase,
    private val getFilesSortByExtDescUseCase: GetFilesSortByExtDescUseCase,
    private val getModifiedFilesUseCase: GetModifiedFilesUseCase
) : ViewModel() {

    private val _files = MutableStateFlow<List<ExternalFileDto>>(ArrayList<ExternalFileDto>())
    val files: StateFlow<List<ExternalFileDto>> = _files

    private val _modifiedFiles =
        MutableStateFlow<List<ExternalFileDto>>(ArrayList<ExternalFileDto>())
    val modifiedFiles: StateFlow<List<ExternalFileDto>> = _modifiedFiles

    fun getFilesByName() {
        viewModelScope.launch {
            kotlin.runCatching {
                getFilesSortByNameUseCase()
            }.onSuccess {
                _files.value = it
            }
        }
    }

    fun getFilesBySizeAsc() {
        viewModelScope.launch {
            kotlin.runCatching {
                getFilesSortBySizeAscUseCase()
            }.onSuccess {
                _files.value = it
            }
        }
    }

    fun getFilesBySizeDesc() {
        viewModelScope.launch {
            kotlin.runCatching {
                getFilesSortBySizeDescUseCase()
            }.onSuccess {
                _files.value = it
            }
        }
    }

    fun getFilesByDateAsc() {
        viewModelScope.launch {
            kotlin.runCatching {
                getFilesSortByDateAscUseCase()
            }.onSuccess {
                _files.value = it
            }
        }
    }

    fun getFilesByDateDesc() {
        viewModelScope.launch {
            kotlin.runCatching {
                getFilesSortByDateDescUseCase()
            }.onSuccess {
                _files.value = it
            }
        }
    }

    fun getFilesByExtAsc() {
        viewModelScope.launch {
            kotlin.runCatching {
                getFilesSortByExtAscUseCase()
            }.onSuccess {
                _files.value = it
            }
        }
    }

    fun getFilesByExtDesc() {
        viewModelScope.launch {
            kotlin.runCatching {
                getFilesSortByExtDescUseCase()
            }.onSuccess {
                _files.value = it
            }
        }
    }

    fun getModifiedFiles(savedFiles: List<ExternalSavedFileDto>) {
        viewModelScope.launch {
            kotlin.runCatching {
                getModifiedFilesUseCase(savedFiles)
            }.onSuccess {
                _modifiedFiles.value = it
            }
        }
    }
}
