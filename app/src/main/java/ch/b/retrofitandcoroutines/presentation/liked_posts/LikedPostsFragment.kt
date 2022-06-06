package ch.b.retrofitandcoroutines.presentation.liked_posts

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.core.logTo
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
                Log.i("TAG",it.logTo().toString())
            })
        }
        viewModel.registration()
    }

    fun newInstance(): LikedPostsFragment {
        return LikedPostsFragment()
    }

}