package ru.nastyaanastasya.filereader.presentation.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log
import kotlinx.coroutines.launch
import ru.nastyaanastasya.filereader.R
import ru.nastyaanastasya.filereader.databinding.FragmentListBinding
import ru.nastyaanastasya.filereader.domain.model.ExternalFileDto
import ru.nastyaanastasya.filereader.presentation.extension.hideLoading
import ru.nastyaanastasya.filereader.presentation.extension.showLoading
import ru.nastyaanastasya.filereader.presentation.rv.FileAdapter
import ru.nastyaanastasya.filereader.presentation.rv.itemDecorator.SpaceItemDecorator
import ru.nastyaanastasya.filereader.presentation.viewmodel.FileViewModel

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {
    private lateinit var binding: FragmentListBinding
    private lateinit var fileAdapter: FileAdapter

    private val viewModel: FileViewModel by viewModels()

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                loadFilesByName()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        fileAdapter = FileAdapter { openFile(it) }

        initObservers()
        initList()
        initRefreshAction()
        initSpinner()
        allowAccess()
    }

    private fun initSpinner() {
        binding.spinner.apply {
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.sort_options,
                android.R.layout.simple_spinner_item
            ).also {
                it.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item
                )
                adapter = it
            }

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    setFilesOrdering(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
    }

    private fun initRefreshAction() {
        with(binding) {
            srlLayout.setOnRefreshListener {
                loadFilesByName()
                spinner.setSelection(0)
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.files.collect { files ->
                updateData(files)
                scrollToTop()
                setLoading(false)
            }
        }
    }

    private fun initList() {
        binding.rvFiles.apply {
            adapter = fileAdapter
            addItemDecoration(
                SpaceItemDecorator(requireContext())
            )
        }
    }

    private fun scrollToTop() {
        binding.rvFiles.layoutManager?.scrollToPosition(0)
    }

    private fun updateData(list: List<ExternalFileDto>) {
        binding.tvItemsCount.text = list.size.toString()
        fileAdapter.submitList(list)
    }

    private fun openFile(path: String) {
        // todo
    }

    private fun allowAccess() {
        if (!checkPermission()) {
            requestPermission()
        } else {
            loadFilesByName()
        }
    }

    private fun setFilesOrdering(position: Int) {
        when (position) {
            0 -> loadFilesByName()
            1 -> loadFilesByDateAsc()
            2 -> loadFilesByDateDesc()
            3 -> loadFilesBySizeAsc()
            4 -> loadFilesBySizeDesc()
            5 -> loadFilesByExtAsc()
            6 -> loadFilesByExtDesc()
            else -> loadModifiedFiles()
        }
    }

    private fun loadFilesByName() {
        setLoading(true)
        viewModel.getFilesByName()
    }

    private fun loadFilesBySizeAsc() {
        setLoading(true)
        viewModel.getFilesBySizeAsc()
    }

    private fun loadFilesBySizeDesc() {
        setLoading(true)
        viewModel.getFilesBySizeDesc()
    }

    private fun loadFilesByDateAsc() {
        setLoading(true)
        viewModel.getFilesByDateAsc()
    }

    private fun loadFilesByDateDesc() {
        setLoading(true)
        viewModel.getFilesByDateDesc()
    }

    private fun loadFilesByExtAsc() {
        setLoading(true)
        viewModel.getFilesByExtAsc()
    }

    private fun loadFilesByExtDesc() {
        setLoading(true)
        viewModel.getFilesByExtDesc()
    }

    private fun loadModifiedFiles() {
        TODO("Not yet implemented")
    }

    private fun requestPermission() {
        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun checkPermission(): Boolean {
        activity?.apply {
            return (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED)
        }
        return false
    }

    private fun setLoading(state: Boolean) {
        with(binding) {
            if (state) {
                srlLayout.isRefreshing = true
                rvFiles.visibility = View.GONE
            } else {
                srlLayout.isRefreshing = false
                rvFiles.visibility = View.VISIBLE
            }
        }
    }
}
