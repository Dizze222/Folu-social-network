package ch.b.retrofitandcoroutines.utils.core_network.authorization.cache

import ch.b.retrofitandcoroutines.utils.core.Reader
import ch.b.retrofitandcoroutines.utils.core_ui.ResourceProvider

interface TokenToSharedPreferences {
    suspend fun saveRefreshToken(refreshToken: String)
    fun readRefreshToken(): String

    suspend fun saveAccessToken(accessToken: String)
    fun readAccessToken(): String


    class Base(
        resourceProvider: ResourceProvider,
        private val reader: Reader
    ) : TokenToSharedPreferences {
        private val sharedPreferences =
            resourceProvider.sharedPreferences()

        override suspend fun saveRefreshToken(refreshToken: String) {
            sharedPreferences.edit().putString(REFRESH_TOKEN_KEY, refreshToken).apply()
        }

        override fun readRefreshToken(): String =
            reader.read(sharedPreferences, REFRESH_TOKEN_KEY)

        override suspend fun saveAccessToken(accessToken: String) {
            sharedPreferences.edit().putString(ACCESS_TOKEN_KEY, accessToken).apply()
        }

        override fun readAccessToken(): String = reader.read(sharedPreferences, ACCESS_TOKEN_KEY)

    }

    class Test : TokenToSharedPreferences {
        override suspend fun saveRefreshToken(refreshToken: String) {
            TODO("Not yet implemented")
        }

        override fun readRefreshToken(): String = "refreshToken"

        override suspend fun saveAccessToken(accessToken: String) {
            TODO("Not yet implemented")
        }

        override fun readAccessToken(): String = "accessToken"

    }

    private companion object {
        private const val REFRESH_TOKEN_KEY = "key-of-refresh-token"
        private const val ACCESS_TOKEN_KEY = "key-of-access-token"
    }
}