package ch.b.retrofitandcoroutines.data.core

import android.content.SharedPreferences

interface SharedPreferencesReader<T> {

    fun read(sharedPreferencesReader: SharedPreferences, key: String) : T

}