package ru.nastyaanastasya.filereader.presentation.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.nastyaanastasya.filereader.domain.model.ExternalSavedFileDto
import ru.nastyaanastasya.filereader.domain.repository.ExternalFileRepository
import ru.nastyaanastasya.filereader.domain.repository.FileRepository

@AndroidEntryPoint
class FileService : Service() {

    @Inject
    lateinit var externalFileRepository: ExternalFileRepository

    @Inject
    lateinit var fileRepository: FileRepository

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        scope.launch {
            val files = fileRepository.getFilesSortByName()
            val newData = mutableListOf<ExternalSavedFileDto>()
            val formatter = SimpleDateFormat("MM/dd/yyyy")

            files.forEach {
                newData.add(
                    ExternalSavedFileDto(it.hash, formatter.parse(it.dateOfEditing))
                )
            }
            externalFileRepository.removeOldData()
            externalFileRepository.saveNewData(newData)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }
}
