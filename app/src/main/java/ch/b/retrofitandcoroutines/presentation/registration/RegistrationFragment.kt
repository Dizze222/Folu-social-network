package ch.b.retrofitandcoroutines.presentation.registration

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.core.logTo
import ch.b.retrofitandcoroutines.databinding.FragmentRegistrationBinding
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
        val name = binding.name.text.toString()
        val secondName = binding.secondName.text.toString()
        val password = binding.password.text.toString()
        binding.registration.setOnClickListener {
            lifecycleScope.launchWhenCreated {

                viewModel.observeCertainPost(this@RegistrationFragment, {
                    Log.i("ROG", it.logTo().toString())
                })
            }
            Log.i("ROG", Integer.parseInt(binding.numberOfPhone.text.toString(),36).toString())
            /*
            viewModel.registration(
                name,
                secondName,
                Integer.parseInt(binding.numberOfPhone.text.toString(),12).toLong(),
                password
            )

             */
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