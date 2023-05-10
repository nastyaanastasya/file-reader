package ru.nastyaanastasya.filereader.presentation.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.nastyaanastasya.filereader.domain.model.ExternalFileDto

class FileAdapter(
    private val action: (String, String) -> Unit
) : ListAdapter<ExternalFileDto, FileHolder>(FileDiffUtilsCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FileHolder = FileHolder.create(parent, action)

    override fun onBindViewHolder(holder: FileHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<ExternalFileDto>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}

class FileDiffUtilsCallback : DiffUtil.ItemCallback<ExternalFileDto>() {

    override fun areItemsTheSame(
        oldItem: ExternalFileDto,
        newItem: ExternalFileDto
    ): Boolean = oldItem.hash == newItem.hash

    override fun areContentsTheSame(
        oldItem: ExternalFileDto,
        newItem: ExternalFileDto
    ): Boolean = oldItem == newItem
}
