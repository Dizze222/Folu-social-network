package ch.b.retrofitandcoroutines.presentation.user_profile

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.FragmentScreen
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.RouterProvider
import ch.b.retrofitandcoroutines.core.PhotoApp
import ch.b.retrofitandcoroutines.databinding.FragmentUserProfileBinding
import ch.b.retrofitandcoroutines.presentation.core.CameraFragment
import ch.b.retrofitandcoroutines.presentation.core.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
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
            val stream = ByteArrayOutputStream()
            selectedImage.compress(Bitmap.CompressFormat.PNG, 80, stream)
            val byteArray = stream.toByteArray()
            val base64String: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
            viewModel.sendImage(base64String)
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

    private fun alertDialog() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        dialog.setMessage("Откуда брать фото?")
        dialog.setTitle("Фото профиля")
        dialog.setPositiveButton("Камера") { _, _ ->
            val nextScreen = FragmentScreen(CameraFragment.newInstance())
            (parentFragment as RouterProvider).router.navigateTo(nextScreen)
            setFragmentResultListener("request_key") { requestKey, bundle ->
                val result = bundle.getString("bundleKey")
                Toast.makeText(requireContext(), result, Toast.LENGTH_LONG).show()
            }
        }
        dialog.setNegativeButton("Галерея") { _, _ ->
            resultLauncher.launch()
        }
        val alertDialog: AlertDialog = dialog.create()
        alertDialog.show()
    }

}