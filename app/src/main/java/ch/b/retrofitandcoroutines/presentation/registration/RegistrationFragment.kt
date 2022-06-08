package ch.b.retrofitandcoroutines.presentation.registration

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.core.BaseSingleRegistrationStringMapper
import ch.b.retrofitandcoroutines.core.logTo
import ch.b.retrofitandcoroutines.databinding.FragmentRegistrationBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.screen.PhotographersFragment
import ch.b.retrofitandcoroutines.presentation.authorization.AuthorizationFragment
import ch.b.retrofitandcoroutines.presentation.container_screens.FragmentScreen
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.presentation.navigate.BackButtonListener
import ch.b.retrofitandcoroutines.presentation.navigate.RouterProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate),
    BackButtonListener {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registration.setOnClickListener {
            binding.errorText.text = ""
            lifecycleScope.launchWhenCreated {
                viewModel.observeCertainPost(this@RegistrationFragment, {
                    it.map {it.map(object : BaseSingleRegistrationStringMapper.SingleStringMapper{
                        override fun map(
                            accessToken: String,
                            refreshToken: String,
                            successRegister: Boolean
                        ) {
                            if (accessToken.isNotEmpty()){
                                val fragment = PhotographersFragment()
                                val nextScreen = FragmentScreen(fragment.newInstance())
                                (parentFragment as RouterProvider).router.navigateTo(nextScreen)
                            }
                        }

                        override fun map(message: String) {
                            binding.errorText.text = message
                        }

                    })

                    }
                })
            }
            val name = binding.name.text.toString()
            val secondName = binding.secondName.text.toString()
            val password = binding.password.text.toString()
            val phone = binding.numberOfPhone.text.toString().toLong()
            viewModel.registration(
                name,
                secondName,
                phone,
                password
            )
        }

    }

    fun newInstance(): RegistrationFragment {
        return RegistrationFragment()
    }

    override fun onBackPressed(): Boolean {
        (parentFragment as RouterProvider).router.exit()
        return true
    }
}