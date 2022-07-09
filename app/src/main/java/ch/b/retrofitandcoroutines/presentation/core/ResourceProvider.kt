package ch.b.retrofitandcoroutines.presentation.core

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import dagger.Provides


interface ResourceProvider {
    fun getString(@StringRes id: Int) : String
    fun provideContext(): Context
    fun drawable(@DrawableRes id: Int) : Drawable?
    fun sharedPreferences() : SharedPreferences
    class Base(private val context: Context) : ResourceProvider {
        override fun getString(id: Int): String = context.getString(id)
        override fun provideContext(): Context = context
        override fun drawable(id: Int): Drawable? = ContextCompat.getDrawable(context, id)
        override fun sharedPreferences() : SharedPreferences{
            return context.getSharedPreferences("unique-key-preferences", Context.MODE_PRIVATE)
        }
    }
}