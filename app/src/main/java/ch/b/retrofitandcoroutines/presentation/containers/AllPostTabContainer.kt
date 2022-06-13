package ch.b.retrofitandcoroutines.presentation.containers

import android.os.Bundle
import ch.b.retrofitandcoroutines.presentation.all_posts.screen.PhotographersFragment
import ch.b.retrofitandcoroutines.FragmentScreen

class AllPostTabContainer : BaseFragmentContainer() {

    companion object {
        const val TAG = "AllPostTabContainerTag"
    }
    fun newInstance() : AllPostTabContainer{
        return AllPostTabContainer()
    }

    override fun getInitialFragmentScreen(params: Bundle?): FragmentScreen {
        return FragmentScreen(PhotographersFragment().newInstance())
    }
}