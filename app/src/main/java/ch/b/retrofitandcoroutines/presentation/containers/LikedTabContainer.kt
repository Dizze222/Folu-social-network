package ch.b.retrofitandcoroutines.presentation.containers

import android.os.Bundle
import ch.b.retrofitandcoroutines.presentation.container_screens.FragmentScreen
import ch.b.retrofitandcoroutines.presentation.liked_posts.LikedPostsFragment

class LikedTabContainer : BaseFragmentContainer() {

    companion object {
        const val TAG = "LikedTabContainerTag"
    }
    fun newInstance() : LikedTabContainer{
        return LikedTabContainer()
    }

    override fun getInitialFragmentScreen(params: Bundle?): FragmentScreen {
        return FragmentScreen(LikedPostsFragment().newInstance())
    }
}