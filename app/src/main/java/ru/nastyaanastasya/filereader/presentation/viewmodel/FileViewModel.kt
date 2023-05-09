package ru.nastyaanastasya.filereader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.nastyaanastasya.filereader.domain.model.ExternalFileDto
import ru.nastyaanastasya.filereader.domain.usecase.GetFilesSortByDateAscUseCase
import ru.nastyaanastasya.filereader.domain.usecase.GetFilesSortByDateDescUseCase
import ru.nastyaanastasya.filereader.domain.usecase.GetFilesSortByExtAscUseCase
import ru.nastyaanastasya.filereader.domain.usecase.GetFilesSortByExtDescUseCase
import ru.nastyaanastasya.filereader.domain.usecase.GetFilesSortByNameUseCase
import ru.nastyaanastasya.filereader.domain.usecase.GetFilesSortBySizeAscUseCase
import ru.nastyaanastasya.filereader.domain.usecase.GetFilesSortBySizeDescUseCase

@HiltViewModel
class FileViewModel @Inject constructor(
    private val getFilesSortByNameUseCase: GetFilesSortByNameUseCase,
    private val getFilesSortBySizeAscUseCase: GetFilesSortBySizeAscUseCase,
    private val getFilesSortBySizeDescUseCase: GetFilesSortBySizeDescUseCase,
    private val getFilesSortByDateAscUseCase: GetFilesSortByDateAscUseCase,
    private val getFilesSortByDateDescUseCase: GetFilesSortByDateDescUseCase,
    private val getFilesSortByExtAscUseCase: GetFilesSortByExtAscUseCase,
    private val getFilesSortByExtDescUseCase: GetFilesSortByExtDescUseCase
) : ViewModel() {

    private val _files = MutableStateFlow<List<ExternalFileDto>>(ArrayList<ExternalFileDto>())
    val files: StateFlow<List<ExternalFileDto>> = _files

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
}
