package ch.b.retrofitandcoroutines.presentation.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.domain.splash.SplashInteractor
import javax.inject.Inject

class SplashViewModelFactory @Inject constructor(private val interceptor: SplashInteractor) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(interceptor) as T
    }
}