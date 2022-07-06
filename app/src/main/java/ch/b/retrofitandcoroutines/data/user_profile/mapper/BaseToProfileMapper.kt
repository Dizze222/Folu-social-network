package ch.b.retrofitandcoroutines.data.user_profile.mapper

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.user_profile.UserProfileData

class BaseToProfileMapper : Abstract.ToProfileMapper<UserProfileData> {
    override fun map(
        name: String,
        secondName: String,
        bio: String,
        image: String
    ): UserProfileData {
        return UserProfileData.Base(name, secondName, bio, image)
    }


}