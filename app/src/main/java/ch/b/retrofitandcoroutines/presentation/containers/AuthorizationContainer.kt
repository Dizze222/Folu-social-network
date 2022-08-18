package ch.b.retrofitandcoroutines.presentation.containers

import android.os.Bundle
import ch.b.retrofitandcoroutines.authorization.presentation.AuthenticationFragment
import ch.b.retrofitandcoroutines.FragmentScreen

class AuthorizationContainer : BaseFragmentContainer() {

    companion object {
        const val TAG = "AuthorizationFragmentTAG"
        fun newInstance(): AuthorizationContainer {
            return AuthorizationContainer()
        }
    }


    override fun getInitialFragmentScreen(params: Bundle?): FragmentScreen {
        return FragmentScreen(AuthenticationFragment.newInstance())
    }


}