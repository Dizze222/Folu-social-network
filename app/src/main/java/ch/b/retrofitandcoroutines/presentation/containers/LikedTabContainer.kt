package ch.b.retrofitandcoroutines.presentation.containers

import android.os.Bundle
import ch.b.retrofitandcoroutines.FragmentScreen
import ch.b.retrofitandcoroutines.presentation.liked_posts.LikedPostsFragment

class LikedTabContainer : BaseFragmentContainer() {

    companion object {
        const val TAG = "LikedTabContainerTag"
    }
    fun newInstance() : LikedTabContainer{
        return LikedTabContainer()
    }

    override fun getInitialFragmentScreen(params: Bundle?): ch.b.retrofitandcoroutines.FragmentScreen {
        return ch.b.retrofitandcoroutines.FragmentScreen(LikedPostsFragment().newInstance())
    }
}