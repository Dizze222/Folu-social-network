package ch.b.retrofitandcoroutines.presentation.user_profile

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import ch.b.retrofitandcoroutines.databinding.FragmentUserProfileBinding
import ch.b.retrofitandcoroutines.presentation.core.*

class UserProfileFragment :
    BaseFragment<FragmentUserProfileBinding>(FragmentUserProfileBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.imageProfile.setOnClickListener {
            openForGetImageLauncher()
        }
    }


    private fun openForGetImageLauncher() {
        resultLauncher.launch("image/*")
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.imageProfile.setImageURI(it)
    }


    companion object {
        fun newInstance(): UserProfileFragment {
            return UserProfileFragment()
        }
    }


}