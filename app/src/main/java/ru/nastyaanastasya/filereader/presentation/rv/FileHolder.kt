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
    private val action: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ExternalFileDto) {
        with(binding) {
            tvFilename.text = item.fileName
            tvDate.text = item.dateOfEditing
            tvSize.text = item.size

            ivIcon.load(FileIconHelper().getIcon(item.fileExtension))

        }
        itemView.setOnClickListener {
            action(item.path)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            action: (String) -> Unit
        ) = FileHolder(
            ItemFileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            action
        )
    }
}
