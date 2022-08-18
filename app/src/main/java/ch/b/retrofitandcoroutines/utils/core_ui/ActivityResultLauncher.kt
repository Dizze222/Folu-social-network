package ch.b.retrofitandcoroutines.utils.core_ui

import androidx.activity.result.ActivityResultLauncher

interface ActivityResultLauncher : Launch<Unit> {

    open class Image(
        private val launcher: ActivityResultLauncher<String>
    ) : ch.b.retrofitandcoroutines.utils.core_ui.ActivityResultLauncher {
        override fun launch() = launcher.launch(TYPE)
        companion object{
            private const val TYPE = "image/*"
        }
    }
}