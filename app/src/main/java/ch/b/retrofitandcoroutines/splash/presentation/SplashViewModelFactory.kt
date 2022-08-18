package ch.b.retrofitandcoroutines.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.splash.domain.SplashInteractor
import javax.inject.Inject

class SplashViewModelFactory @Inject constructor(private val interceptor: SplashInteractor) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(interceptor) as T
    }
}