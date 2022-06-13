package ch.b.retrofitandcoroutines.presentation.splash_screen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.data.core.authorization.Reader
import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.databinding.FragmentSplashBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.screen.PhotographersFragment
import ch.b.retrofitandcoroutines.presentation.authentication.AuthenticationFragment
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.BackButtonListener
import kotlinx.coroutines.delay


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate),
    BackButtonListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = TokenToSharedPreferences.Base(activity!!.applicationContext, Reader())
        hideNavBar(true)
        lifecycleScope.launchWhenCreated {
            delay(2000)
            if (sharedPref.readAccessToken().isEmpty()) {
                val fragment = AuthenticationFragment()
                val nextScreen = ch.b.retrofitandcoroutines.FragmentScreen(fragment.newInstance())
                (parentFragment as ch.b.retrofitandcoroutines.RouterProvider).router.navigateTo(nextScreen)
            } else {
                val fragment = PhotographersFragment()
                val nextScreen = ch.b.retrofitandcoroutines.FragmentScreen(fragment.newInstance())
                (parentFragment as ch.b.retrofitandcoroutines.RouterProvider).router.navigateTo(nextScreen)
            }
        }
    }

    fun newInstance(): SplashFragment {
        return SplashFragment()
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}