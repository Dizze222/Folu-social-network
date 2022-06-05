package ch.b.retrofitandcoroutines.presentation.liked_posts

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.databinding.FragmentLikedBinding
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.presentation.registration.RegisterViewModel
import ch.b.retrofitandcoroutines.presentation.registration.RegistrationUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikedPostsFragment : BaseFragment<FragmentLikedBinding>(FragmentLikedBinding::inflate) {
    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.observeCertainPost(this@LikedPostsFragment, {
                it.map {registration->
                    registration.map(object : RegistrationUI.StringMapper {
                        override fun map(
                            accessToken: String,
                            refreshToken: String,
                            successRegister: Boolean
                        ) {
                            Log.i(
                                "REG",
                                "access token: ${accessToken}, " +
                                        "refreshToken: ${refreshToken}, " +
                                        "successRegister: $successRegister"
                            )
                        }

                        override fun map(message: String) {
                            Log.i("REG",message)
                        }

                    })
                }
            })
        }
        viewModel.registration()
    }

    fun newInstance(): LikedPostsFragment {
        return LikedPostsFragment()
    }

}