package ch.b.retrofitandcoroutines.favourite_post.presentation.core

import android.content.Context
import ch.b.retrofitandcoroutines.R

object FavouriteNames {

    fun namesOfTab(context: Context, index: Int): String {
        return context.resources.getStringArray(R.array.names_of_tabs)[index]
    }

}
