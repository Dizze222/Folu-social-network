package ch.b.retrofitandcoroutines.domain.user_profile

import ch.b.retrofitandcoroutines.data.user_profile.UserProfileData
import ch.b.retrofitandcoroutines.data.user_profile.UserProfileListData
import ch.b.retrofitandcoroutines.data.user_profile.mapper.UserProfileDataToDomainMapper
import ch.b.retrofitandcoroutines.data.user_profile.mapper.UserProfileListDataToDomainMapper

class BaseUserProfileListDataToDomainMapper(
    private val mapper: UserProfileDataToDomainMapper<UserProfileDomain>
) : UserProfileListDataToDomainMapper(){
    override fun map(error: String): UserProfileListDomain =
        UserProfileListDomain.Fail(error)

    override fun map(): UserProfileListDomain {
        TODO("Not yet implemented")
    }

    override fun map(data: List<UserProfileData>): UserProfileListDomain =
        UserProfileListDomain.Success(data,mapper)


}