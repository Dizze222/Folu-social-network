package ch.b.retrofitandcoroutines.data.core

import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val tokenToSharedPreferences: TokenToSharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${tokenToSharedPreferences.readAccessToken()}").build()
        return chain.proceed(request)
    }
}