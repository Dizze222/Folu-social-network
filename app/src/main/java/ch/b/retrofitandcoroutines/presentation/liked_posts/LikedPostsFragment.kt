package ch.b.retrofitandcoroutines.presentation.liked_posts

import android.os.Bundle
import android.view.View
import ch.b.retrofitandcoroutines.databinding.FragmentLikedBinding
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment


class LikedPostsFragment : BaseFragment<FragmentLikedBinding>(FragmentLikedBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    fun newInstance(): LikedPostsFragment {
        return LikedPostsFragment()
    }

}