package ch.b.retrofitandcoroutines.presentation.core

import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.Mapper

interface ImageProfile : Base64Image<BaseImage>,Show<ViewWrapper>,Mapper<Unit,ImageProfile> {


    data class Base(
        private val uri: android.net.Uri
    ) : ImageProfile {
        override fun base64Image(param: BaseImage): String =
            param.base64Image(uri)

        override fun show(arg: ViewWrapper) {
            TODO("Not yet implemented")
        }

        override fun map(src: Unit): ImageProfile {
            TODO("Not yet implemented")
        }


    }

    object Empty : ImageProfile {
        override fun base64Image(param: BaseImage): String =
            param.base64Image(R.drawable.ic_download)

        override fun show(arg: ViewWrapper) {
            arg.map(R.drawable.ic_collect)
        }

        override fun map(src: Unit): ImageProfile {
            TODO("Not yet implemented")
        }

    }
    data class Bitmap(
        val bitmap: android.graphics.Bitmap
    ) : ImageProfile {
        override fun base64Image(param: BaseImage): String = param.base64Image(bitmap)


        override fun show(image: ViewWrapper) = image.showBitmap(bitmap)
        override fun map(src: Unit) = Bitmap(bitmap)
    }
    data class Uri(
        private val uri: android.net.Uri
    ) : ImageProfile {

        override fun base64Image(param: BaseImage) = param.base64Image(uri)
        override fun show(arg: ViewWrapper) {
            TODO("Not yet implemented")
        }

        override fun map(src: Unit): ImageProfile {
            TODO("Not yet implemented")
        }


    }
}