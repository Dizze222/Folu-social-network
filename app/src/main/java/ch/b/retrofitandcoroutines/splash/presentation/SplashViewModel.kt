package ch.b.retrofitandcoroutines.splash.presentation

import androidx.lifecycle.ViewModel
import ch.b.retrofitandcoroutines.splash.domain.SplashInteractor

class SplashViewModel(
    private val interceptor: SplashInteractor
) : ViewModel() {
    suspend fun checkAccessToken() {
        return interceptor.checkAccessToken()
    }
}