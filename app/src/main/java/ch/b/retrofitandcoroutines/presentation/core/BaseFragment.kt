package ch.b.retrofitandcoroutines.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.presentation.liked_posts.LikedPostsFragment

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

open class BaseFragment<B : ViewBinding>(private val inflate: Inflate<B>) : Fragment() {

    private var _viewBinding: B? = null
    val binding get() = checkNotNull(_viewBinding)
    var navBar: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = inflate.invoke(inflater, container, false)
        navBar = activity!!.findViewById(R.id.navigation)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}