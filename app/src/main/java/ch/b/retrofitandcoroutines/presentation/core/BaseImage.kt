package ch.b.retrofitandcoroutines.presentation.core

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.core.graphics.drawable.toBitmap
import ch.b.retrofitandcoroutines.presentation.all_posts.ResourceProvider
import java.io.ByteArrayOutputStream

interface BaseImage : Base64Image<Uri> {
    fun base64Image(drawableId: Int): String

    class Base(
        private val resourceProvider: ResourceProvider,
        private val contentResolver: ContentResolver,
        private val byteArrayOutputStream: ByteArrayOutputStream
    ) : BaseImage {

        override fun base64Image(imageUri: Uri) : String {
            val input = contentResolver.openInputStream(imageUri)
            val image = BitmapFactory.decodeStream(input, null, null)
            image!!.compress(Bitmap.CompressFormat.JPEG, QUALITY, byteArrayOutputStream)
            val imageBytes = byteArrayOutputStream.toByteArray()

            return Base64.encodeToString(imageBytes, Base64.DEFAULT)
        }

        override fun base64Image(drawableId: Int): String {
            val drawable  = resourceProvider.drawable(drawableId)
            val bitmap = drawable.toBitmap()
            bitmap.compress(Bitmap.CompressFormat.JPEG, QUALITY, byteArrayOutputStream)
            val imageBytes = byteArrayOutputStream.toByteArray()

            return Base64.encodeToString(imageBytes, Base64.DEFAULT)
        }


        private companion object {
            private const val QUALITY = 100
        }
    }
}