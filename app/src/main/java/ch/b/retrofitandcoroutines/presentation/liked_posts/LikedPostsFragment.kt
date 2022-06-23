package ch.b.retrofitandcoroutines.presentation.liked_posts

import android.os.Bundle
import android.view.View
import ch.b.retrofitandcoroutines.databinding.FragmentLikedBinding
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment


class LikedPostsFragment : BaseFragment<FragmentLikedBinding>(FragmentLikedBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    companion object {
        private const val ARGS_KEY_TAB_NAME = "ARGS_KEY_TAB_NAME"
        fun newInstance(tabName: String): LikedPostsFragment {
            val args = Bundle()
            args.putString(ARGS_KEY_TAB_NAME, tabName)
            val fragment = LikedPostsFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
