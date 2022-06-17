package ch.b.retrofitandcoroutines.data.splash

import retrofit2.http.GET


interface SplashService {

    @GET("/splash")
    suspend fun checkAccessToken()
}
