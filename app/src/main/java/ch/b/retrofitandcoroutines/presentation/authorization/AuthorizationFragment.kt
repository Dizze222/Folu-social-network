package ch.b.retrofitandcoroutines.presentation.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.databinding.FragmentAuthorizationBinding
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment

class AuthorizationFragment : BaseFragment<FragmentAuthorizationBinding>(FragmentAuthorizationBinding::inflate) {
    private val a: Int = 3
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_authorization, container, false)
    }


}