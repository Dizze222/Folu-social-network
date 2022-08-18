package ch.b.retrofitandcoroutines.presentation.splash_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.core.Reader
import ch.b.retrofitandcoroutines.data.core.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.databinding.FragmentSplashBinding
import ch.b.retrofitandcoroutines.all_posts.presentation.PhotographersFragment
import ch.b.retrofitandcoroutines.presentation.authentication.AuthenticationFragment
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.BackButtonListener
import ch.b.retrofitandcoroutines.FragmentScreen
import ch.b.retrofitandcoroutines.RouterProvider
import ch.b.retrofitandcoroutines.core.PhotoApp
import ch.b.retrofitandcoroutines.presentation.core.ResourceProvider
import kotlinx.coroutines.delay
import javax.inject.Inject


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate),
    BackButtonListener {

    @Inject
    lateinit var splashViewModelFactory: SplashViewModelFactory

    private val viewModel: SplashViewModel by viewModels {
        splashViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref =
            TokenToSharedPreferences.Base(ResourceProvider.Base(requireContext()), Reader())
        hideNavBar(true)
        lifecycleScope.launchWhenCreated {
            delay(1000)
            if (sharedPref.readAccessToken().isEmpty()) {
                val fragment = AuthenticationFragment
                val nextScreen = FragmentScreen(fragment.newInstance())
                (parentFragment as RouterProvider).router.navigateTo(nextScreen)
            } else {
                viewModel.checkAccessToken()
                val fragment = PhotographersFragment
                val nextScreen = FragmentScreen(fragment.newInstance())
                (parentFragment as RouterProvider).router.navigateTo(nextScreen)
            }
        }
    }

    companion object {
        fun newInstance(): SplashFragment {
            return SplashFragment()
        }
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
