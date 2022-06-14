package ch.b.retrofitandcoroutines.data.authorization.net.authorization

import android.util.Log
import ch.b.retrofitandcoroutines.data.authorization.net.update_token.UpdateTokenService
import ch.b.retrofitandcoroutines.data.core.TokenInterceptor
import ch.b.retrofitandcoroutines.data.core.TokenInterceptorRefresh
import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.di.module.NetworkModule
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.Authenticator
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Authenticator(
    private val tokenFromShared: TokenToSharedPreferences
) :
    Authenticator {
    override fun authenticate(route: Route?, response: Response): Request = runBlocking {


        Log.i("HTTP", "401 code")
        val okHttp = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptorRefresh(tokenFromShared))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://97d9-84-39-247-98.ngrok.io/")
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(UpdateTokenService::class.java)

        val a = api.updateToken(tokenFromShared.readRefreshToken())
        tokenFromShared.saveRefreshToken(a.refreshToken)
        tokenFromShared.saveAccessToken(a.accessToken)

        return@runBlocking response.request.newBuilder()
            .header("Authorization", "Bearer" + tokenFromShared.readAccessToken())
            .build()
    }
}
