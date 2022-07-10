package ch.b.retrofitandcoroutines.presentation.user_profile.galary_picker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import javax.inject.Inject

class ImagePickerViewModelFactory @Inject constructor(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImagePickerViewModel(application) as T
    }
}