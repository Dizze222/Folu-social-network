package ch.b.retrofitandcoroutines.data.core

import android.content.Context

interface TokenToSharedPreferences {
    fun save(refreshToken: String)

    fun read() : String

    fun userIsAuthorized() : Boolean

    class Base(
        context: Context,
        private val reader: Reader
    ) : TokenToSharedPreferences {
        private val sharedPreferences =
            context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)

        override fun save(refreshToken: String) {
            sharedPreferences.edit().putString(USER_KEY,refreshToken).apply()
        }

        override fun read(): String =
            reader.read(sharedPreferences, USER_KEY)

        override fun userIsAuthorized(): Boolean =
            read().isNotEmpty()

    }

    private companion object {
        private const val KEY_PREFERENCES = "unique-key-preferences"
        private const val USER_KEY = "unique-key"
    }
}