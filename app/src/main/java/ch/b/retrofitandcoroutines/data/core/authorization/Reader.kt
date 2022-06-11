package ch.b.retrofitandcoroutines.data.core.authorization

import android.content.SharedPreferences
import ch.b.retrofitandcoroutines.data.core.authorization.cache.SharedPreferencesReader

class Reader : SharedPreferencesReader<String> {
    override fun read(sharedPreferencesReader: SharedPreferences, key: String): String =
        sharedPreferencesReader.getString(key,"") ?: "no data"
}