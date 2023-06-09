package ru.nastyaanastasya.filereader.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.nastyaanastasya.filereader.databinding.ItemFileBinding
import ru.nastyaanastasya.filereader.domain.model.ExternalFileDto
import ru.nastyaanastasya.filereader.presentation.helper.FileIconHelper

class FileHolder(
    private val binding: ItemFileBinding,
    private val actionView: (String, String) -> Unit,
    private val actionSend: (String, String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ExternalFileDto) {
        with(binding) {
            tvFilename.text = item.fileName
            tvDate.text = item.dateOfEditing
            tvSize.text = item.size

            ivIcon.load(FileIconHelper().getIcon(item.fileExtension))

        }
        itemView.setOnClickListener {
            it.findFocus()
            actionView(item.path, item.fileExtension.name)
        }
        itemView.setOnLongClickListener {
            actionSend(item.path, item.fileExtension.name)
            true
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            actionView: (String, String) -> Unit,
            actionSend: (String, String) -> Unit,
        ) = FileHolder(
            ItemFileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            actionView,
            actionSend
        )
    }
}
