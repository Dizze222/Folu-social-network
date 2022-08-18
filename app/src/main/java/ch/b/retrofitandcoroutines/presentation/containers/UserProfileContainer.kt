package ch.b.retrofitandcoroutines.presentation.containers

import android.os.Bundle
import ch.b.retrofitandcoroutines.FragmentScreen
import ch.b.retrofitandcoroutines.user_profile.presentation.UserProfileFragment

class UserProfileContainer : BaseFragmentContainer(){

    companion object {
        const val TAG = "UserProfileTag"
        fun newInstance(): UserProfileContainer {
            return UserProfileContainer()
        }

    }

    override fun getInitialFragmentScreen(params: Bundle?): FragmentScreen {
        return FragmentScreen(UserProfileFragment.newInstance())
    }


}