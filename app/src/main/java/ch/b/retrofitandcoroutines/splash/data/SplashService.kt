package ch.b.retrofitandcoroutines.splash.data

import retrofit2.http.GET


interface SplashService {

    @GET("/splash")
    suspend fun checkAccessToken()
}
