package ch.b.retrofitandcoroutines.presentation.user_profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import ch.b.retrofitandcoroutines.core.PhotoApp
import ch.b.retrofitandcoroutines.databinding.FragmentUserProfileBinding
import ch.b.retrofitandcoroutines.presentation.core.*
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class UserProfileFragment :
    BaseFragment<FragmentUserProfileBinding>(FragmentUserProfileBinding::inflate) {
    @Inject
    lateinit var authenticationViewModelFactory: UserProfileViewModelFactory
    private val viewModel: UserProfileViewModel by viewModels {
        authenticationViewModelFactory
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.imageProfile.setOnClickListener {
            resultLauncher.launch()
        }
        binding.smileOfProfile.text = "\uD83D\uDC7A"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    private var resultLauncher = ActivityResultLauncher.Image(registerForActivityResult(ActivityResultContracts.GetContent()) {
        val imageStream = requireActivity().contentResolver.openInputStream(it)
        val selectedImage: Bitmap = BitmapFactory.decodeStream(imageStream)
        val stream = ByteArrayOutputStream()
        selectedImage.compress(Bitmap.CompressFormat.PNG,80,stream)
        val byteArray= stream.toByteArray()
        val base64String = Base64.encodeToString(byteArray, Base64.DEFAULT)
        val decodedString = Base64.decode(base64String, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        binding.imageProfile.setImageBitmap(decodedByte)
    })


    companion object {
        fun newInstance(): UserProfileFragment {
            return UserProfileFragment()
        }
    }

    fun inject() {
        val application = requireActivity().application as PhotoApp
        val appComponent = application.appComponent
        appComponent.inject(this)
    }
}