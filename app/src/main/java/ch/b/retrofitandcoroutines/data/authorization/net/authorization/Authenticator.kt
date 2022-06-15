package ch.b.retrofitandcoroutines.data.authorization.net.authorization

import android.util.Log
import ch.b.retrofitandcoroutines.core.BaseSingleRegistrationStringMapper
import ch.b.retrofitandcoroutines.data.authorization.net.update_token.UpdateTokenService
import ch.b.retrofitandcoroutines.data.core.TokenInterceptor
import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.Authenticator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import androidx.annotation.NonNull





class Authenticator(
    private val tokenFromShared: TokenToSharedPreferences,
    private val scope: CoroutineScope
) :
    Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        Log.i("TOKEN", response.toString())
        val okHttp = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor.RefreshToken(tokenFromShared))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://photographer-application.herokuapp.com/")
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(UpdateTokenService::class.java)
        if (responseCount(response) >= 3) {
            Log.i("TOKEN", "return null")
            return null
        }
            scope.launch {
                val result = api.updateToken()
                result.map(object : BaseSingleRegistrationStringMapper.AuthenticatorStringMapper {
                    override suspend fun map(accessToken: String, refreshToken: String) {
                        tokenFromShared.saveAccessToken(accessToken)
                        tokenFromShared.saveRefreshToken(refreshToken)
                        Log.i("TOKEN", "ACCESSTOKEN: $accessToken ; REFRESHTOKEN: $refreshToken")

                    }
                })
            }

        return response.request
            .newBuilder()
            .addHeader("Authorization", "Bearer ${tokenFromShared.readAccessToken()}")
            .build()

    }

    private fun responseCount(response: Response): Int {
        var responsee: Response = response
        var result = 1
        while (responsee.priorResponse.also {
                if (it != null) {
                    responsee = it
                }
            } != null) {
            result++
        }
        return result
    }

    private fun isRequestWithAccessToken(response: Response): Boolean {
        val header = response.request.header("Authorization")
        return header != null && header.startsWith("Bearer")
    }

    private fun newRequestWithAccessToken(request: Request, accessToken: String): Request {
        return request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
    }
}



