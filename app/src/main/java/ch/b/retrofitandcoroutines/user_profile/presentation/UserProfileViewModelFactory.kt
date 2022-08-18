package ch.b.retrofitandcoroutines.user_profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.user_profile.domain.UserProfileInteractor
import ch.b.retrofitandcoroutines.user_profile.domain.UserProfileListDomainToUiMapper
import ch.b.retrofitandcoroutines.user_profile.presentation.core.UserProfileCommunication
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