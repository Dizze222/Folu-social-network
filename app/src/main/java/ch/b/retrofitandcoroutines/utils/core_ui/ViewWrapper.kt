package ch.b.retrofitandcoroutines.utils.core_ui

import android.graphics.Bitmap
import android.widget.ImageView
import ch.b.retrofitandcoroutines.utils.core.Mapper

interface ViewWrapper :
    Mapper<Int, Unit>,
    ShowBitmap {

    override fun map(src: Int) = Unit
    override fun showBitmap(bitmap: Bitmap) = Unit

    class Image(
        private val imageView: ImageView,
        private val resourceProvider: ResourceProvider
    ) : ViewWrapper {
        override fun map(src: Int) = imageView.setImageDrawable(resourceProvider.drawable(src))

        override fun showBitmap(bitmap: Bitmap) {
            imageView.setImageBitmap(bitmap)
        }

    }
}