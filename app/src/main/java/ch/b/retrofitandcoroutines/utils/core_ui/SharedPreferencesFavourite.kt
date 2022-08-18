package ch.b.retrofitandcoroutines.utils.core_ui

import android.content.Context
import ch.b.retrofitandcoroutines.utils.core.Reader

interface SharedPreferencesFavourite {

    fun saveFavouritePost(id: String)

    fun readIdOfFavouritePost(): String

    class Base(context: Context, private val reader: Reader) : SharedPreferencesFavourite {

        private val sharedPreferences =
            context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)


        override fun saveFavouritePost(id: String) {
            sharedPreferences.edit().putString(FAVOURITE_KEY, id).apply()
        }

        override fun readIdOfFavouritePost(): String =
            reader.read(sharedPreferences, FAVOURITE_KEY)

    }


    private companion object {
        private const val KEY_PREFERENCES = "unique-two-key-preferences"
        private const val FAVOURITE_KEY = "key-of-favourite-post"
    }


}