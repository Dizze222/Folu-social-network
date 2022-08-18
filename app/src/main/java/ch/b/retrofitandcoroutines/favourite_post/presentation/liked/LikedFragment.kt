package ch.b.retrofitandcoroutines.favourite_post.presentation.liked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.databinding.FragmentLikedBinding
import ch.b.retrofitandcoroutines.utils.core_ui.BaseFragment

class LikedFragment : BaseFragment<FragmentLikedBinding>(FragmentLikedBinding::inflate) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_liked, container, false)
    }
    companion object{
        fun newInstance() : LikedFragment {
            return LikedFragment()
        }
    }

}