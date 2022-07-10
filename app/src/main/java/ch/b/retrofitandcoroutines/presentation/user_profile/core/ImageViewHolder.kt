package ch.b.retrofitandcoroutines.presentation.user_profile.core

import ch.b.retrofitandcoroutines.databinding.ItemImageBinding
import ch.b.retrofitandcoroutines.presentation.galary_picker.MediaStoreImage

class ImageViewHolder(
    private val binding: ItemImageBinding,
    private val onClick: ((MediaStoreImage?) -> Unit)? = null
) : BaseViewHolder<MediaStoreImage, ItemImageBinding>(binding) {
    init {
        binding.image.setOnClickListener {
            onClick?.invoke(getRowItem())
        }
    }

    override fun bind() {
        getRowItem()?.let {
            binding.image.setImageURI(it.mapUri())
        }
    }
}
