package ch.b.retrofitandcoroutines.utils.core_ui

import android.widget.ImageView
import ch.b.retrofitandcoroutines.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

interface ImageLoad {

    fun load(imageView: ImageView)

    class Base(private val url: String?) : ImageLoad {
        override fun load(imageView: ImageView) {
            Glide.with(imageView.context)
                .load(url)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .placeholder(R.color.colorGrey)
                .priority(Priority.IMMEDIATE)
                .error(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }
}