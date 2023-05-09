package ru.nastyaanastasya.filereader.presentation.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class FileService: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
