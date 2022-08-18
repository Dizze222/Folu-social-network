package ch.b.retrofitandcoroutines.user_profile.domain

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.user_profile.data.UserProfileData
import ch.b.retrofitandcoroutines.user_profile.data.mapper.UserProfileDataToDomainMapper
import ch.b.retrofitandcoroutines.user_profile.presentation.UserProfileListUi

sealed class UserProfileListDomain :
    Abstract.Object<UserProfileListUi, UserProfileListDomainToUiMapper> {

    class Success(
        private val listOfUserProfile: List<UserProfileData>,
        private val profileMapper: UserProfileDataToDomainMapper<UserProfileDomain>
    ) :
        UserProfileListDomain() {
        override fun map(mapper: UserProfileListDomainToUiMapper): UserProfileListUi {
            val profileListDomain = listOfUserProfile.map {
                it.map(profileMapper)
            }
            return mapper.map(profileListDomain)
        }


    }

    class Fail(private val error: String) : UserProfileListDomain() {
        override fun map(mapper: UserProfileListDomainToUiMapper): UserProfileListUi =
            mapper.map(error)
    }

}