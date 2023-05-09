package ru.nastyaanastasya.filereader.presentation.extension

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import ru.nastyaanastasya.filereader.R
import ru.nastyaanastasya.filereader.presentation.MainActivity

fun Fragment.showLoading(){
    (activity as MainActivity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
}

fun Fragment.hideLoading(){
    (activity as MainActivity).findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
}
