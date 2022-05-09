package ch.b.retrofitandcoroutines.presentation.core

import android.media.Image
import android.net.Uri
import ch.b.retrofitandcoroutines.R

interface ImageProfile : Base64Image<BaseImage>{

    data class Base(
        private val uri: Uri
    ) : ImageProfile{
        override fun base64Image(param: BaseImage): String = param.base64Image(uri)
    }

    object Empty : ImageProfile{
        override fun base64Image(param: BaseImage): String =
            param.base64Image(R.drawable.ic_download)

    }

}