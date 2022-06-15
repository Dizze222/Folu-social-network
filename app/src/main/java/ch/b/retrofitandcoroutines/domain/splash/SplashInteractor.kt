package ch.b.retrofitandcoroutines.domain.splash

import ch.b.retrofitandcoroutines.data.splash.SplashService

interface SplashInteractor {
    suspend fun splash(): Boolean
    class Base(private val splashService: SplashService) : SplashInteractor {
        override suspend fun splash(): Boolean {
            return splashService.splash()
        }
    }
}