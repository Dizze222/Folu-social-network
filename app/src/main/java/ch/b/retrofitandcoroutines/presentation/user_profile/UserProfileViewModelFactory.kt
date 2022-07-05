package ch.b.retrofitandcoroutines.presentation.user_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.data.user_profile.network.UserProfileCloudDataSource
import javax.inject.Inject

class UserProfileViewModelFactory @Inject constructor(private val userProfileDataSource: UserProfileCloudDataSource) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserProfileViewModel(userProfileDataSource) as T
    }
}