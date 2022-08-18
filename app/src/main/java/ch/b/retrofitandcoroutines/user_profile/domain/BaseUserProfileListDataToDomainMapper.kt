package ch.b.retrofitandcoroutines.user_profile.domain

import ch.b.retrofitandcoroutines.user_profile.data.UserProfileData
import ch.b.retrofitandcoroutines.user_profile.data.mapper.UserProfileDataToDomainMapper
import ch.b.retrofitandcoroutines.user_profile.data.mapper.UserProfileListDataToDomainMapper

class BaseUserProfileListDataToDomainMapper(
    private val mapper: UserProfileDataToDomainMapper<UserProfileDomain>
) : UserProfileListDataToDomainMapper(){
    override fun map(error: String): UserProfileListDomain =
        UserProfileListDomain.Fail(error)

    override fun map(): UserProfileListDomain {
        TODO("Not yet implemented")
    }

    override fun map(data: List<UserProfileData>): UserProfileListDomain =
        UserProfileListDomain.Success(data, mapper)


}