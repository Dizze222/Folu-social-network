package ch.b.retrofitandcoroutines.data.user_profile

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerListData
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.data.user_profile.mapper.UserProfileListDataToDomainMapper
import ch.b.retrofitandcoroutines.domain.user_profile.UserProfileListDomain

sealed class UserProfileListData :
    Abstract.Object<UserProfileListDomain, UserProfileListDataToDomainMapper> {


    class Base(private val listOfUserProfile: List<UserProfileData>) : UserProfileListData() {
        override fun map(mapper: UserProfileListDataToDomainMapper): UserProfileListDomain =
            mapper.map(listOfUserProfile)
    }

    class Fail(private val e: String) : UserProfileListData() {
        override fun map(mapper: UserProfileListDataToDomainMapper) = mapper.map(e)
    }

}