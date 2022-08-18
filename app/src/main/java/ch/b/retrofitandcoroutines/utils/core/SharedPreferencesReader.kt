package ch.b.retrofitandcoroutines.utils.core

import android.content.SharedPreferences

interface SharedPreferencesReader<T> {

    fun read(sharedPreferencesReader: SharedPreferences, key: String) : T

}