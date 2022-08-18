package ch.b.retrofitandcoroutines.user_profile.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.b.retrofitandcoroutines.user_profile.domain.UserProfileInteractor
import ch.b.retrofitandcoroutines.user_profile.domain.UserProfileListDomainToUiMapper
import ch.b.retrofitandcoroutines.user_profile.presentation.core.UserProfileCommunication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun getUserProfile() {
        communication.map(listOf(UserProfileUi.Progress))
        viewModelScope.launch {
            val resultDomain = interactor.getProfileInfo()
            withContext(Dispatchers.Main) {
                val resultUi = resultDomain.map(mapper)
                resultUi.map(communication)
            }
        }
    }

    suspend fun observer(owner: LifecycleOwner, observer: FlowCollector<List<UserProfileUi>>) {
        communication.observe(owner, observer)
    }


}