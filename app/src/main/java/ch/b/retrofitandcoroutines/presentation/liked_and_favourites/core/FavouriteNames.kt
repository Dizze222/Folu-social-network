package ch.b.retrofitandcoroutines.presentation.liked_and_favourites.core

import android.content.Context
import ch.b.retrofitandcoroutines.R

object FavouriteNames {

    fun namesOfTab(context: Context, index: Int): String {
        return context.resources.getStringArray(R.array.names_of_tabs)[index]
    }

}
