package ch.b.retrofitandcoroutines.presentation.authentication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.BaseSingleRegistrationStringMapper
import ch.b.retrofitandcoroutines.core.PhotoApp
import ch.b.retrofitandcoroutines.databinding.FragmentAuthorizationBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographersFragment
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationFragment
import javax.inject.Inject


class AuthenticationFragment :
    BaseFragment<FragmentAuthorizationBinding>(FragmentAuthorizationBinding::inflate) {

    @Inject
    lateinit var authenticationViewModelFactory: AuthenticationViewModelFactory
    private val viewModel: AuthenticationViewModel by viewModels() {
        authenticationViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registration.setOnClickListener {
            val fragment = RegistrationFragment()
            val nextScreen = ch.b.retrofitandcoroutines.FragmentScreen(fragment.newInstance())
            (parentFragment as ch.b.retrofitandcoroutines.RouterProvider).router.navigateTo(
                nextScreen
            )
        }
        binding.authentication.setOnClickListener {
            try {
                lifecycleScope.launchWhenCreated {
                    viewModel.observer(this@AuthenticationFragment, { list ->
                        list.map {
                            it.map(object : BaseSingleRegistrationStringMapper.SingleStringMapper {
                                override fun map(
                                    accessToken: String,
                                    refreshToken: String,
                                    successRegister: Boolean
                                ) {
                                    if (accessToken.isNotEmpty()) {
                                        val fragment = PhotographersFragment()
                                        val nextScreen =
                                            ch.b.retrofitandcoroutines.FragmentScreen(fragment.newInstance())
                                        (parentFragment as ch.b.retrofitandcoroutines.RouterProvider).router.navigateTo(
                                            nextScreen
                                        )
                                    }
                                }

                                override fun map(message: String) {
                                    binding.state.text = message
                                }

                                override fun map(progress: Boolean) {
                                    binding.state.text = activity!!.getString(R.string.please_wait)
                                }

                            })
                        }

                    })
                }
                val phone = binding.numberOfPhone.text.toString().toLong()
                val password = binding.password.text.toString()
                viewModel.authentication(phone, password)
            } catch (e: Exception) {
                binding.state.text = "ошибка"
            }
        }

    }

    fun newInstance(): AuthenticationFragment {
        return AuthenticationFragment()
    }

    fun inject() {
        val app = requireActivity().application as PhotoApp
        val component = app.appComponent
        component.inject(this)
    }

}