package ch.b.retrofitandcoroutines.data.core

import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import okhttp3.Interceptor
import okhttp3.Response


interface TokenInterceptor : Interceptor {

    class AccessToken(private val tokenToSharedPreferences: TokenToSharedPreferences) :
        TokenInterceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer ${tokenToSharedPreferences.readAccessToken()}"
                )
                .build()
            return chain.proceed(request)
        }
    }

    class RefreshToken(private val tokenToSharedPreferences: TokenToSharedPreferences) :
        TokenInterceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer ${tokenToSharedPreferences.readRefreshToken()}"
                )
                .build()


            return chain.proceed(request)
        }
    }
}

