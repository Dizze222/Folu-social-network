package ch.b.retrofitandcoroutines.presentation.splash_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.data.core.authorization.Reader
import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.databinding.FragmentSplashBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.screen.PhotographersFragment
import ch.b.retrofitandcoroutines.presentation.authentication.AuthenticationFragment
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.BackButtonListener
import ch.b.retrofitandcoroutines.core.PhotoApp
import kotlinx.coroutines.delay
import javax.inject.Inject


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate),
    BackButtonListener {

    @Inject
    lateinit var splashViewModelFactory: SplashViewModelFactory

    private val viewModel: SplashViewModel by viewModels{
        splashViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = TokenToSharedPreferences.Base(activity!!.applicationContext, Reader())
        hideNavBar(true)
        lifecycleScope.launchWhenCreated {
            delay(2000)
            viewModel.splash()
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
    private fun inject() {
        val application = requireActivity().application as PhotoApp
        val appComponent = application.appComponent
        appComponent.inject(this)
    }
}