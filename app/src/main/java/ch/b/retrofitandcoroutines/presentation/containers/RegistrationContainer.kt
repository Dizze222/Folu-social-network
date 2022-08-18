package ch.b.retrofitandcoroutines.presentation.containers

import android.os.Bundle
import ch.b.retrofitandcoroutines.FragmentScreen
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationFragment

class RegistrationContainer : BaseFragmentContainer() {
    companion object{
        const val TAG = "RegistrationFragment"
        fun newInstance() : RegistrationContainer{
            return RegistrationContainer()
        }
    }

    override fun getInitialFragmentScreen(params: Bundle?): FragmentScreen {
        return FragmentScreen(RegistrationFragment.newInstance())
    }


}