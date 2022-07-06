package ch.b.retrofitandcoroutines.domain.user_profile

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.user_profile.UserProfileData
import ch.b.retrofitandcoroutines.data.user_profile.mapper.UserProfileDataToDomainMapper
import ch.b.retrofitandcoroutines.presentation.user_profile.UserProfileListUi

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