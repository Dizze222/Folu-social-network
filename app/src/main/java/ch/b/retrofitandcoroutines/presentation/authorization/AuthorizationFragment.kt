package ch.b.retrofitandcoroutines.presentation.authorization

import android.os.Bundle
import android.view.View
import android.widget.Toast
import ch.b.retrofitandcoroutines.databinding.FragmentAuthorizationBinding
import ch.b.retrofitandcoroutines.presentation.container_screens.FragmentScreen
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.presentation.navigate.RouterProvider
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationFragment : BaseFragment<FragmentAuthorizationBinding>(FragmentAuthorizationBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registration.setOnClickListener {
            val fragment = RegistrationFragment()
            val nextScreen = FragmentScreen(fragment.newInstance())
            (parentFragment as RouterProvider).router.navigateTo(nextScreen)
        }

    }

    fun newInstance() : AuthorizationFragment{
        return AuthorizationFragment()
    }


}