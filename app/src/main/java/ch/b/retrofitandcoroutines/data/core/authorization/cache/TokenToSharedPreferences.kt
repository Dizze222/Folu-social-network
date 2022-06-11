package ch.b.retrofitandcoroutines.data.core.authorization.cache

import android.content.Context
import android.util.Log
import ch.b.retrofitandcoroutines.data.core.authorization.Reader

interface TokenToSharedPreferences {
    fun saveRefreshToken(refreshToken: String)
    fun readRefreshToken(): String

    suspend fun saveAccessToken(accessToken: String)
    fun readAccessToken(): String


    class Base(
        context: Context,
        private val reader: Reader
    ) : TokenToSharedPreferences {
        private val sharedPreferences =
            context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)

        override fun saveRefreshToken(refreshToken: String) {
            sharedPreferences.edit().putString(REFRESH_TOKEN_KEY, refreshToken).apply()
        }

        override fun readRefreshToken(): String =
            reader.read(sharedPreferences, REFRESH_TOKEN_KEY)

        override suspend fun saveAccessToken(accessToken: String) {
            sharedPreferences.edit().putString(ACCESS_TOKEN_KEY, accessToken).apply()
        }

        override fun readAccessToken(): String {
            Log.i("TOKE", reader.read(sharedPreferences, ACCESS_TOKEN_KEY))
            return reader.read(sharedPreferences, ACCESS_TOKEN_KEY)
        }
    }

    private companion object {
        private const val KEY_PREFERENCES = "unique-key-preferences"
        private const val REFRESH_TOKEN_KEY = "key-of-refresh-token"
        private const val ACCESS_TOKEN_KEY = "key-of-access-token"
    }
}