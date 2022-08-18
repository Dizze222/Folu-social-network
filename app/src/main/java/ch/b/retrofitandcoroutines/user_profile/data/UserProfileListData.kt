package ch.b.retrofitandcoroutines.user_profile.data

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.user_profile.data.mapper.UserProfileListDataToDomainMapper
import ch.b.retrofitandcoroutines.user_profile.domain.UserProfileListDomain

sealed class UserProfileListData :
    Abstract.Object<UserProfileListDomain, UserProfileListDataToDomainMapper> {


    class Success(private val listOfUserProfile: List<UserProfileData>) : UserProfileListData() {
        override fun map(mapper: UserProfileListDataToDomainMapper): UserProfileListDomain =
            mapper.map(listOfUserProfile)
    }

    class Fail(private val e: String) : UserProfileListData() {
        override fun map(mapper: UserProfileListDataToDomainMapper) = mapper.map(e)
    }

}