package ch.b.retrofitandcoroutines.presentation.splash_screen

import androidx.lifecycle.ViewModel
import ch.b.retrofitandcoroutines.domain.splash.SplashInteractor

class SplashViewModel(
    private val interceptor: SplashInteractor
) : ViewModel() {
    suspend fun splash(): Boolean {
        return interceptor.splash()
    }
}