package ch.b.retrofitandcoroutines.presentation.liked_posts

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.core.logTo
import ch.b.retrofitandcoroutines.databinding.FragmentLikedBinding
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.presentation.registration.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LikedPostsFragment : BaseFragment<FragmentLikedBinding>(FragmentLikedBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun newInstance(): LikedPostsFragment {
        return LikedPostsFragment()
    }
}
