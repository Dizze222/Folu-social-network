package ch.b.retrofitandcoroutines.presentation.galary_picker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import ch.b.retrofitandcoroutines.databinding.ItemImageBinding
import ch.b.retrofitandcoroutines.user_profile.presentation.core.BaseRecyclerAdapter
import ch.b.retrofitandcoroutines.user_profile.presentation.core.ImageViewHolder

class GalleryAdapter constructor(
    private val onItemClick: ((MediaStoreImage?) -> Unit)? = null,
) : BaseRecyclerAdapter<MediaStoreImage, ItemImageBinding, ImageViewHolder>(CountryItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick = onItemClick
        )
}

class CountryItemDiffUtil : DiffUtil.ItemCallback<MediaStoreImage>() {
    override fun areItemsTheSame(oldItem: MediaStoreImage, newItem: MediaStoreImage): Boolean {
        return oldItem.mapId() == newItem.mapId()
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: MediaStoreImage, newItem: MediaStoreImage): Boolean {
        return oldItem == newItem
    }
}
