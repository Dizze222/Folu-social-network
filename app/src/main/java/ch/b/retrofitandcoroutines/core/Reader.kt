package ch.b.retrofitandcoroutines.core

import android.content.SharedPreferences

class Reader : SharedPreferencesReader<String> {
    override fun read(sharedPreferencesReader: SharedPreferences, key: String): String =
        sharedPreferencesReader.getString(key,"") ?: "no data"
}