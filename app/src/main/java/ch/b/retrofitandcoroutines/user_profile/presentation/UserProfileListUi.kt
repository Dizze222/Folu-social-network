package ch.b.retrofitandcoroutines.user_profile.presentation

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.user_profile.domain.UserProfileDomain
import ch.b.retrofitandcoroutines.user_profile.presentation.core.UserProfileCommunication

sealed class UserProfileListUi : Abstract.Object<Unit, UserProfileCommunication> {

    class Success(
        private val listOfUserProfile: List<UserProfileDomain>,
        private val profileMapper: UserProfileDomainToUiMapper<UserProfileUi>
    ) : UserProfileListUi(){
        override fun map(mapper: UserProfileCommunication) {
            val userProfileList = listOfUserProfile.map {
                it.map(profileMapper)
            }
            mapper.map(userProfileList)
        }
    }

    class Fail(
        private val message: String
    ) : UserProfileListUi(){
        override fun map(mapper: UserProfileCommunication) {
            mapper.map(listOf(UserProfileUi.Fail(message)))
        }

    }


}