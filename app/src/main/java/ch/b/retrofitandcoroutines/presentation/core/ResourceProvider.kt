package ch.b.retrofitandcoroutines.presentation.core

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

interface ResourceProvider {
    fun getString(@StringRes id: Int) : String
    fun provideContext(): Context
    fun drawable(@DrawableRes id: Int) : Drawable
    class Base(private val context: Context) : ResourceProvider {
        override fun getString(id: Int): String = context.getString(id)
        override fun provideContext(): Context = context
        override fun drawable(id: Int): Drawable = ContextCompat.getDrawable(context, id)!!
    }
}