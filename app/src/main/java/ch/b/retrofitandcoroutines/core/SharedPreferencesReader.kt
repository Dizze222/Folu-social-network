package ch.b.retrofitandcoroutines.core

import android.content.SharedPreferences

interface SharedPreferencesReader<T> {

    fun read(sharedPreferencesReader: SharedPreferences, key: String) : T

}