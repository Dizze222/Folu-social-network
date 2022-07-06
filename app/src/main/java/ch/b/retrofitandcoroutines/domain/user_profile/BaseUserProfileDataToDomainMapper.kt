package ch.b.retrofitandcoroutines.domain.user_profile

import ch.b.retrofitandcoroutines.data.user_profile.mapper.UserProfileDataToDomainMapper

class BaseUserProfileDataToDomainMapper : UserProfileDataToDomainMapper<UserProfileDomain> {
    override fun map(
        name: String,
        secondName: String,
        bio: String,
        image: String
    ): UserProfileDomain {
        return UserProfileDomain.Base(name,secondName,bio,image)
    }


}