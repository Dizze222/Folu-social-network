package ch.b.retrofitandcoroutines.user_profile.domain

import ch.b.retrofitandcoroutines.user_profile.data.mapper.UserProfileDataToDomainMapper

class BaseUserProfileDataToDomainMapper : UserProfileDataToDomainMapper<UserProfileDomain> {
    override fun map(
        name: String,
        secondName: String,
        bio: String,
        image: String
    ): UserProfileDomain {
        return UserProfileDomain.Base(name, secondName, bio, image)
    }


}