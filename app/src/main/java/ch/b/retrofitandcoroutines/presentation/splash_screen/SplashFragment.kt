package ch.b.retrofitandcoroutines.presentation.splash_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.data.core.Reader
import ch.b.retrofitandcoroutines.data.core.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.databinding.FragmentSplashBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.screen.PhotographersFragment
import ch.b.retrofitandcoroutines.presentation.authorization.AuthorizationFragment
import ch.b.retrofitandcoroutines.presentation.certain_post.PhotographerDetailFragment
import ch.b.retrofitandcoroutines.presentation.container_screens.FragmentScreen
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.presentation.navigate.BackButtonListener
import ch.b.retrofitandcoroutines.presentation.navigate.RouterProvider
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import android.text.Html
import android.view.View.inflate


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate),
    BackButtonListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = TokenToSharedPreferences.Base(activity!!.applicationContext, Reader())
        hideNavBar(true)
        lifecycleScope.launchWhenCreated {
            delay(7000)
            if (sharedPref.readRefreshToken().isEmpty()) {
                val fragment = AuthorizationFragment()
                val nextScreen = FragmentScreen(fragment.newInstance())
                (parentFragment as RouterProvider).router.navigateTo(nextScreen)
            } else {
                val fragment = PhotographersFragment()
                val nextScreen = FragmentScreen(fragment.newInstance())
                (parentFragment as RouterProvider).router.navigateTo(nextScreen)
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