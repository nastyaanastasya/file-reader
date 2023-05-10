package ru.nastyaanastasya.filereader.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import ru.nastyaanastasya.filereader.R
import ru.nastyaanastasya.filereader.presentation.extension.findController
import ru.nastyaanastasya.filereader.presentation.service.FileService

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = findController(R.id.container)

        startService(Intent(this, FileService::class.java))
    }

    override fun onDestroy() {
        stopService(Intent(this, FileService::class.java))
        super.onDestroy()
    }
}
