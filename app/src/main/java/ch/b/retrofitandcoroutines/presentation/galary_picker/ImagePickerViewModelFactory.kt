package ch.b.retrofitandcoroutines.presentation.galary_picker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ImagePickerViewModelFactory @Inject constructor(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImagePickerViewModel(application) as T
    }
}