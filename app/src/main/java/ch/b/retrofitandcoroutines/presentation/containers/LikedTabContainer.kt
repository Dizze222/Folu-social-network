package ch.b.retrofitandcoroutines.presentation.containers

import android.os.Bundle
import ch.b.retrofitandcoroutines.FragmentScreen
import ch.b.retrofitandcoroutines.favourite_post.presentation.FavouritePostsFragment

class LikedTabContainer : BaseFragmentContainer() {

    companion object {
        const val TAG = "LikedTabContainerTag"
        fun newInstance() : LikedTabContainer{
            return LikedTabContainer()
        }
    }

    override fun getInitialFragmentScreen(params: Bundle?): FragmentScreen {
        return FragmentScreen(FavouritePostsFragment.newInstance())
    }
}