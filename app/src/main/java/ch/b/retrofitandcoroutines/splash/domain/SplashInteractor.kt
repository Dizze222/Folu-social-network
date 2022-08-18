package ch.b.retrofitandcoroutines.splash.domain

import ch.b.retrofitandcoroutines.splash.data.SplashService

interface SplashInteractor {
    suspend fun checkAccessToken()
    class Base(private val splashService: SplashService) : SplashInteractor {
        override suspend fun checkAccessToken() {
            return splashService.checkAccessToken()
        }
    }
}