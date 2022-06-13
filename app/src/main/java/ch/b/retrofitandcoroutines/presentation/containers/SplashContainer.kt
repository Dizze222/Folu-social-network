package ch.b.retrofitandcoroutines.presentation.containers

import android.os.Bundle
import ch.b.retrofitandcoroutines.FragmentScreen
import ch.b.retrofitandcoroutines.presentation.splash_screen.SplashFragment

class SplashContainer : BaseFragmentContainer() {

    companion object{
        const val TAG = "SplashScreenContainerTag"
    }

    fun newInstance() : SplashContainer{
        return SplashContainer()
    }

    override fun getInitialFragmentScreen(params: Bundle?): ch.b.retrofitandcoroutines.FragmentScreen {
        return ch.b.retrofitandcoroutines.FragmentScreen(SplashFragment().newInstance())
    }


}