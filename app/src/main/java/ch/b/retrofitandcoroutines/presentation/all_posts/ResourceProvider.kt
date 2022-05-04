package ch.b.retrofitandcoroutines.presentation.all_posts

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes id: Int) : String
    fun provideContext(): Context
    class Base(private val context: Context) : ResourceProvider {
        override fun getString(id: Int): String = context.getString(id)
        override fun provideContext(): Context = context

    }
}