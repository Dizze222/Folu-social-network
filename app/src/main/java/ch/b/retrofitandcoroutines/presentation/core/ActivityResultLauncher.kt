package ch.b.retrofitandcoroutines.presentation.core

import androidx.activity.result.ActivityResultLauncher

interface ActivityResultLauncher : Launch<Unit> {

    open class Image(
        private val launcher: ActivityResultLauncher<String>
    ) : ch.b.retrofitandcoroutines.presentation.core.ActivityResultLauncher{
        override fun launch() = launcher.launch(TYPE)
        companion object{
            private const val TYPE = "image/*"
        }
    }
}