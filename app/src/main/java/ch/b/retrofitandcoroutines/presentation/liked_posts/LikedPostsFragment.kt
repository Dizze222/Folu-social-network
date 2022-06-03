package ch.b.retrofitandcoroutines.presentation.liked_posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ch.b.retrofitandcoroutines.R
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