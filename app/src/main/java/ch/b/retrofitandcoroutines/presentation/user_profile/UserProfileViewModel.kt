package ch.b.retrofitandcoroutines.presentation.user_profile

import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.b.retrofitandcoroutines.data.user_profile.network.UserProfileCloudDataSource
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class UserProfileViewModel(private val userProfileDataSource: UserProfileCloudDataSource) : ViewModel() {

    fun sendImage(bitmap: Bitmap) {
        viewModelScope.launch {
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,80,stream)
            val byteArray = stream.toByteArray()
            val encoded = Base64.encodeToString(byteArray,Base64.DEFAULT)

            Log.i("RRR",encoded)
        }
    }
}