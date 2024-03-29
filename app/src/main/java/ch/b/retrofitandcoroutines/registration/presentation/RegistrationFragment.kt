package ch.b.retrofitandcoroutines.registration.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.all_posts.presentation.core.BaseSingleRegistrationStringMapper
import ch.b.retrofitandcoroutines.utils.core.PhotoApp
import ch.b.retrofitandcoroutines.databinding.FragmentRegistrationBinding
import ch.b.retrofitandcoroutines.all_posts.presentation.PhotographersFragment
import ch.b.retrofitandcoroutines.utils.core_ui.BaseFragment
import ch.b.retrofitandcoroutines.BackButtonListener
import ch.b.retrofitandcoroutines.RouterProvider
import javax.inject.Inject


class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate),
    BackButtonListener {

    @Inject
    lateinit var registrationViewModelFactory: RegistrationViewModelFactory

    private val viewModel: RegisterViewModel by viewModels() {
        registrationViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registration.setOnClickListener {
            try {
                lifecycleScope.launchWhenCreated {
                    viewModel.observe(this@RegistrationFragment) { list ->
                        list.map {
                            it.map(object : BaseSingleRegistrationStringMapper.SingleStringMapper {
                                override fun map(
                                    accessToken: String,
                                    refreshToken: String,
                                    successRegister: Boolean
                                ) {
                                    if (accessToken.isNotEmpty()) {
                                        val fragment = PhotographersFragment
                                        val nextScreen =
                                            ch.b.retrofitandcoroutines.FragmentScreen(fragment.newInstance())
                                        (parentFragment as RouterProvider).router.navigateTo(
                                            nextScreen
                                        )
                                    }
                                }

                                override fun map(message: String) {
                                    binding.errorText.text = message
                                }

                                override fun map(progress: Boolean) {
                                    binding.errorText.text =
                                        activity!!.getString(R.string.please_wait)
                                }
                            })
                        }
                    }
                }
                val name = binding.name.text.toString()
                val secondName = binding.secondName.text.toString()
                val password = binding.password.text.toString()
                val phone = binding.numberOfPhone.text.toString().toLong()
                viewModel.registration(name, secondName, phone, password)
            } catch (e: Exception) {
                binding.errorText.text = "Неизвестная Ошибка"
            }
        }
    }

    companion object {
        fun newInstance(): RegistrationFragment {
            return RegistrationFragment()
        }
    }

    override fun onBackPressed(): Boolean {
        (parentFragment as RouterProvider).router.exit()
        return true
    }

    private fun inject() {
        val application = requireActivity().application as PhotoApp
        val appComponent = application.appComponent
        appComponent.inject(this)
    }
}