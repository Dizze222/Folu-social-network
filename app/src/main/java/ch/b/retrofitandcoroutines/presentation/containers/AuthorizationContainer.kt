package ch.b.retrofitandcoroutines.presentation.containers

import android.os.Bundle
import ch.b.retrofitandcoroutines.presentation.authentication.AuthenticationFragment
import ch.b.retrofitandcoroutines.presentation.container_screens.FragmentScreen

class AuthorizationContainer : BaseFragmentContainer() {

    companion object{
        const val TAG = "AuthorizationFragmentTAG"
    }

    fun newInstance() : AuthorizationContainer{
        return AuthorizationContainer()
    }

    override fun getInitialFragmentScreen(params: Bundle?): FragmentScreen {
        return FragmentScreen(AuthenticationFragment().newInstance())
    }


}