package ru.nastyaanastasya.filereader.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.nastyaanastasya.filereader.domain.model.ExternalSavedFileDto
import ru.nastyaanastasya.filereader.domain.usecase.db.DeleteOldDataUseCase
import ru.nastyaanastasya.filereader.domain.usecase.db.GetSavedExternalFilesUseCase
import ru.nastyaanastasya.filereader.domain.usecase.db.SaveNewDataUseCase

@HiltViewModel
class ExternalFileViewModel @Inject constructor(
    private val getSavedExternalFilesUseCase: GetSavedExternalFilesUseCase,
    private val deleteOldDataUseCase: DeleteOldDataUseCase,
    private val saveNewDataUseCase: SaveNewDataUseCase
) : ViewModel() {

    private val _oldFiles =
        MutableStateFlow<List<ExternalSavedFileDto>>(ArrayList<ExternalSavedFileDto>())
    val oldFiles: StateFlow<List<ExternalSavedFileDto>> = _oldFiles

    fun getOldFiles() {
        viewModelScope.launch {
            kotlin.runCatching {
                getSavedExternalFilesUseCase()
            }.onSuccess {
                _oldFiles.value = it
            }
        }
    }

    fun deleteOldFiles() {
        viewModelScope.launch {
            kotlin.runCatching {
                deleteOldDataUseCase()
            }.onSuccess {
                Log.i("SAVED_FILES", "Remove old files")
            }
        }
    }

    fun saveNewData(newData: List<ExternalSavedFileDto>) {
        viewModelScope.launch {
            kotlin.runCatching {
                saveNewDataUseCase(newData)
            }.onSuccess {
                Log.i("SAVED_FILES", "Save new files")
            }
        }
    }
}
