package ch.b.retrofitandcoroutines.presentation.user_profile

import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.b.retrofitandcoroutines.data.user_profile.network.UserProfileCloudDataSource
import ch.b.retrofitandcoroutines.domain.user_profile.UserProfileInteractor
import ch.b.retrofitandcoroutines.domain.user_profile.UserProfileListDomainToUiMapper
import ch.b.retrofitandcoroutines.presentation.user_profile.core.UserProfileCommunication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.util.concurrent.Flow

class UserProfileViewModel(
    private val interactor: UserProfileInteractor,
    private val communication: UserProfileCommunication,
    private val mapper: UserProfileListDomainToUiMapper
) : ViewModel() {

    fun sendImage(base64: String) {
        viewModelScope.launch {
           interactor.sendPhoto(base64)
        }
    }
    fun getUserProfile(){
       viewModelScope.launch {
           val resultDomain = interactor.getProfileInfo()
           withContext(Dispatchers.Main){
               val resultUi = resultDomain.map(mapper)
               resultUi.map(communication)
           }
       }
    }

    suspend fun observer(owner: LifecycleOwner, observer: FlowCollector<List<UserProfileUi>>){
        communication.observe(owner,observer)
    }


}