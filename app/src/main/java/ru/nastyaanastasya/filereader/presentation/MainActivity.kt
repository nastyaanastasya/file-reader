package ru.nastyaanastasya.filereader.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import ru.nastyaanastasya.filereader.R
import ru.nastyaanastasya.filereader.presentation.extension.findController

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = findController(R.id.container)
    }
}
