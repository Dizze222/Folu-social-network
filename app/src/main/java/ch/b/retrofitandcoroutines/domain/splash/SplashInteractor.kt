package ch.b.retrofitandcoroutines.domain.splash

import ch.b.retrofitandcoroutines.data.splash.SplashService

interface SplashInteractor {
    suspend fun checkAccessToken()
    class Base(private val splashService: SplashService) : SplashInteractor {
        override suspend fun checkAccessToken() {
            return splashService.checkAccessToken()
        }
    }
}