package ch.b.retrofitandcoroutines.presentation.user_profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.FragmentScreen
import ch.b.retrofitandcoroutines.RouterProvider
import ch.b.retrofitandcoroutines.core.PhotoApp
import ch.b.retrofitandcoroutines.databinding.FragmentUserProfileBinding
import ch.b.retrofitandcoroutines.presentation.core.CameraFragment
import ch.b.retrofitandcoroutines.presentation.core.*
import java.io.ByteArrayOutputStream
import java.util.*
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
            alertDialog()
        }
        lifecycleScope.launchWhenCreated {
            viewModel.observer(this@UserProfileFragment) {
                it.map { userProfileUi ->
                    userProfileUi.map(
                        binding.name,
                        binding.imageProfile,
                        binding.bio,
                        binding.progress,
                        binding.mainLayout
                    )
                    userProfileUi.map(binding.errorLayout, binding.progress, binding.errorTextView)
                }
            }
        }
        viewModel.getUserProfile()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }


    private var resultLauncher =
        ActivityResultLauncher.Image(registerForActivityResult(ActivityResultContracts.GetContent()) {
            val imageStream = requireActivity().contentResolver.openInputStream(it)
            val selectedImage: Bitmap = BitmapFactory.decodeStream(imageStream)
            binding.imageProfile.setImageBitmap(selectedImage)
            val stream = ByteArrayOutputStream()
            selectedImage.compress(Bitmap.CompressFormat.PNG, 80, stream)
            val byteArray = stream.toByteArray()
            val base64String: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
            viewModel.sendImage(base64String)
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

    private fun alertDialog() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        dialog.setMessage("Откуда брать фото?")
        dialog.setTitle("Фото профиля")
        dialog.setPositiveButton("Камера") { _, _ ->
            val nextScreen = FragmentScreen(CameraFragment.newInstance())
            (parentFragment as RouterProvider).router.navigateTo(nextScreen)
            setFragmentResultListener("request_key") { _, bundle ->
                val result = bundle.getString("bundleKey")
                Toast.makeText(requireContext(), result, Toast.LENGTH_LONG).show()
                val decodedString = Base64.decode(result, Base64.DEFAULT)
                val decodedByte =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                binding.imageProfile.setImageBitmap(decodedByte)
                viewModel.sendImage(result!!)
            }
        }
        dialog.setNegativeButton("Галерея") { _, _ ->
            resultLauncher.launch()
        }
        val alertDialog: AlertDialog = dialog.create()
        alertDialog.show()
    }

}