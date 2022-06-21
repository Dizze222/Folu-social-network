package ch.b.retrofitandcoroutines.presentation.containers

import android.os.Bundle
import ch.b.retrofitandcoroutines.FragmentScreen
import ch.b.retrofitandcoroutines.presentation.user_search.UserSearchFragment

class SearchTabContainer : BaseFragmentContainer() {
    companion object {
        const val TAG = "SearchFragmentTAG"
    }

    fun newInstance(): SearchTabContainer {
        return SearchTabContainer()
    }

    override fun getInitialFragmentScreen(params: Bundle?): FragmentScreen {
        return FragmentScreen(UserSearchFragment().newInstance())
    }

}