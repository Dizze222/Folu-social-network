package ch.b.retrofitandcoroutines.data.core.authorization.cache

import android.content.SharedPreferences

interface SharedPreferencesReader<T> {

    fun read(sharedPreferencesReader: SharedPreferences, key: String) : T

}