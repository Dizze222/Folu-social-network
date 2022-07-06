package ch.b.retrofitandcoroutines.presentation.user_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.domain.user_profile.UserProfileInteractor
import ch.b.retrofitandcoroutines.domain.user_profile.UserProfileListDomainToUiMapper
import ch.b.retrofitandcoroutines.presentation.user_profile.core.UserProfileCommunication
import javax.inject.Inject


class UserProfileViewModelFactory @Inject constructor(
    private val interactor: UserProfileInteractor,
    private val communication: UserProfileCommunication,
    private val mapper: UserProfileListDomainToUiMapper
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserProfileViewModel(interactor, communication, mapper) as T
    }

}